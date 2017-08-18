package cn.bvin.android.app.gzmap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;


public class MainActivity extends AppCompatActivity {


    private AMap mAMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mAMap = fragment.getMap();
        LatLng southwest = new LatLng(25.58105,114.704015);
        LatLng northeast = new LatLng(25.932933,115.089223);
        LatLngBounds lb = new LatLngBounds(southwest,northeast);
        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(lb,0));

        DistrictSearch districtSearch = new DistrictSearch(this);
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.setKeywords("赣州市");
        districtSearchQuery.setShowBoundary(true);
        districtSearchQuery.setShowChild(true);
        districtSearch.setQuery(districtSearchQuery);
        districtSearch.setOnDistrictSearchListener(new DistrictSearch.OnDistrictSearchListener() {
            @Override
            public void onDistrictSearched(final DistrictResult districtResult) {

                if (districtResult == null || districtResult.getDistrict()==null) {
                    return;
                }
                if(districtResult.getAMapException() != null && districtResult.getAMapException().getErrorCode() == AMapException.CODE_AMAP_SUCCESS){


                    new Thread() {
                        public void run() {
                            //赣州市
                            DistrictItem self = districtResult.getDistrict().get(0);

                            //下瞎区县
                            for (DistrictItem item : self.getSubDistrict()) {

                                String name = item.getName();

                                    //Log.d("onDistrictSearched: ",item.toString());
                                    DistrictSearch districtSearch = new DistrictSearch(MainActivity.this);
                                    DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
                                    districtSearchQuery.setKeywords(name);
                                    districtSearchQuery.setShowBoundary(true);
                                    districtSearchQuery.setShowChild(true);
                                    districtSearch.setQuery(districtSearchQuery);
                                    try {
                                        DistrictResult result = districtSearch.searchDistrict();
                                        if (result != null) {
                                            DistrictItem selfDistrict = result.getDistrict().get(0);
                                            Log.d("onDistrictSearched: ",selfDistrict.toString());
                                            if (name.lastIndexOf("区")==name.length()-1){
                                                addDistrictItem(selfDistrict,Color.RED);
                                            }else {
                                                addDistrictItem(selfDistrict,Color.YELLOW);
                                            }
                                        }
                                    } catch (AMapException e) {
                                        e.printStackTrace();
                                    }

                            }
                        }
                    }.start();
                }
            }
        });
        districtSearch.searchDistrictAsyn();
    }

    private void addDistrictItem( DistrictItem item, int color) {

        if (item == null) {
            return;
        }
        String[] polyStr = item.districtBoundary();
        if (polyStr == null || polyStr.length == 0) {
            Log.d( "addDistrictItem: ","nothing");
            return;
        }
        Log.d( "addDistrictItem: ","something");
        for (String str : polyStr) {
            String[] lat = str.split(";");
            PolylineOptions polylineOption = new PolylineOptions();
            boolean isFirst = true;
            LatLng firstLatLng = null;
            for (String latstr : lat) {
                String[] lats = latstr.split(",");
                if (isFirst) {
                    isFirst = false;
                    firstLatLng = new LatLng(Double
                            .parseDouble(lats[1]), Double
                            .parseDouble(lats[0]));
                }
                polylineOption.add(new LatLng(Double
                        .parseDouble(lats[1]), Double
                        .parseDouble(lats[0])));
            }
            if (firstLatLng != null) {
                polylineOption.add(firstLatLng);
            }

            polylineOption.width(10).color(color);
            mAMap.addPolyline(polylineOption);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //mMapView.onSaveInstanceState(outState);
    }
}
