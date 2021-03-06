package cn.bvin.android.app.calculator.ground;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner mSpSquareUnit;
    private EditText mEtSquare;
    private Spinner mSpFar;
    private EditText mEtFar;
    private Spinner mSpPriceUnit;
    private EditText mEtPrice;
    private TextView mTvResult;

    private SharedPreferences mPreferences;

    private TextView mTvGroundCode;
    private TextView mTvGroundSquare;
    private TextView mTvFar;
    private TextView mTvPrice;

    private EditText mEtGroundCode;
    private TextView mTvBuildPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSpSquareUnit = (Spinner) findViewById(R.id.sp_square_unit);
        mEtSquare = (EditText) findViewById(R.id.et_square);
        mSpFar = (Spinner) findViewById(R.id.sp_far);
        mEtFar = (EditText) findViewById(R.id.et_far);
        mSpPriceUnit = (Spinner) findViewById(R.id.sp_price_unit);
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mTvResult = (TextView) findViewById(R.id.tv_result);

        mEtGroundCode = (EditText) findViewById(R.id.et_ground_code);

        mTvGroundCode = (TextView) findViewById(R.id.tv_ground_code);
        mTvGroundSquare = (TextView) findViewById(R.id.tv_ground_square);
        mTvFar = (TextView) findViewById(R.id.tv_far);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvBuildPrice = (TextView) findViewById(R.id.tv_build_price);

        mPreferences = getPreferences(MODE_PRIVATE);

        mEtGroundCode.setText(mPreferences.getString(mTvGroundCode.getText().toString(), null));
        mEtSquare.setText(mPreferences.getString(mTvGroundSquare.getText().toString(), null));
        mEtFar.setText(mPreferences.getString(mTvFar.getText().toString(), null));
        mEtPrice.setText(mPreferences.getString(mTvPrice.getText().toString(), null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==R.id.action_calculate){
            View view = getCurrentFocus();
            if (view != null) {
                view.clearFocus();//清除焦点，光标消失
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);//隐藏输入法
            }
            BigDecimal groundSquare =  new BigDecimal(mEtSquare.getText().toString());//亩
            BigDecimal price = new BigDecimal(mEtPrice.getText().toString());
            price = price.multiply(new BigDecimal(10000));//换算元
            BigDecimal totalPrice = price;
            if (mSpPriceUnit.getSelectedItemId() == 0) {//万元/亩
                totalPrice = price.multiply(groundSquare);//只要面积和单价单位一致便可直接换算
            }

            if (mSpSquareUnit.getSelectedItemId() == 0) {//亩
                groundSquare = groundSquare.multiply(new BigDecimal(666.6666667));//换算平方米
            }
            BigDecimal far = new BigDecimal(mEtFar.getText().toString());
            BigDecimal buildingSquare = groundSquare.multiply(far);//土地面积*容积率
            BigDecimal result = totalPrice.divide(buildingSquare,BigDecimal.ROUND_HALF_EVEN);
            mTvResult.setText(result.toString()+"元/㎡");
            mPreferences.edit()
                    .putString(mTvGroundCode.getText().toString(),mEtGroundCode.getText().toString())
                    .putString(mTvGroundSquare.getText().toString(),mEtSquare.getText().toString())
                    .putString(mTvFar.getText().toString(),mEtFar.getText().toString())
                    .putString(mTvPrice.getText().toString(),mEtPrice.getText().toString())
                    .putString(mTvBuildPrice.getText().toString(),mTvResult.getText().toString())
                    .apply();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.empty_container,new DetailsFragment()).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
