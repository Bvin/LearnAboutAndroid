package cn.bvin.android.app.groundcalculator;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private Spinner spSquareUnit;
    private EditText etSquare;
    private Spinner spFar;
    private EditText etFar;
    private Spinner spPriceUnit;
    private EditText etPrice;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        spSquareUnit = (Spinner) findViewById(R.id.sp_square_unit);
        etSquare = (EditText) findViewById(R.id.et_square);
        spFar = (Spinner) findViewById(R.id.sp_far);
        etFar = (EditText) findViewById(R.id.et_far);
        spPriceUnit = (Spinner) findViewById(R.id.sp_price_unit);
        etPrice = (EditText) findViewById(R.id.et_price);
        tvResult = (TextView) findViewById(R.id.tv_result);

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
            BigDecimal groundSquare =  new BigDecimal(etSquare.getText().toString());//亩
            BigDecimal price = new BigDecimal(etPrice.getText().toString());
            price = price.multiply(new BigDecimal(10000));//换算元
            BigDecimal totalPrice = price.multiply(groundSquare);//只要面积和单价单位一致便可直接换算
            groundSquare = groundSquare.multiply(new BigDecimal(666.6666667));//换算平方米
            BigDecimal far = new BigDecimal(etFar.getText().toString());
            BigDecimal buildingSquare = groundSquare.multiply(far);//土地面积*容积率
            BigDecimal result = totalPrice.divide(buildingSquare,BigDecimal.ROUND_HALF_EVEN);
            tvResult.setText(result.toString()+"元/㎡");
        }
        return true;
    }
}
