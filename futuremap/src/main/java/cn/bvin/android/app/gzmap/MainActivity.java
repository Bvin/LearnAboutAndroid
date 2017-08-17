package cn.bvin.android.app.gzmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        AMap map = fragment.getMap();
        LatLng southwest = new LatLng(25.58105,114.704015);
        LatLng northeast = new LatLng(25.932933,115.089223);
        LatLngBounds lb = new LatLngBounds(southwest,northeast);
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(lb,0));

        DistrictSearch districtSearch = new DistrictSearch(this);
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.setKeywords("赣州市");
        districtSearchQuery.setShowBoundary(true);
        districtSearchQuery.setShowChild(true);
        districtSearch.setQuery(districtSearchQuery);
        districtSearch.setOnDistrictSearchListener(new DistrictSearch.OnDistrictSearchListener() {
            @Override
            public void onDistrictSearched(DistrictResult districtResult) {
                Log.d("onDistrictSearched: ",districtResult.toString());
            }
        });
        districtSearch.searchDistrictAsyn();
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
