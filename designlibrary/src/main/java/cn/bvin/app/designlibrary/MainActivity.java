package cn.bvin.app.designlibrary;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.bvin.app.designlibrary.coordinator.CoordinatorActivity;
import cn.bvin.app.designlibrary.coordinator.ItemTouchHelperActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bvv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CoordinatorActivity.start(MainActivity.this);
                ItemTouchHelperActivity.start(MainActivity.this);
            }
        });
    }
}
