package com.waimai.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;

    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;

    private MainFragment mainFragment;

    private DingdanFragment dingdanFragment;

    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING).setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_solid,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.list_solid,"订单"))
                .addItem(new BottomNavigationItem(R.drawable.user_regular,"我的"))
                .setFirstSelectedPosition(0).initialise();
                //.setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.home_solid));

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                switch (position){
                    case 0:
                        mainFragment = new MainFragment();
                        transaction.replace(R.id.center_layout,mainFragment);
                        break;
                    case 1:
                        dingdanFragment = new DingdanFragment();
                        transaction.replace(R.id.center_layout,dingdanFragment);
                        break;
                    case 2:
                        meFragment = new MeFragment();
                        transaction.replace(R.id.center_layout,meFragment);
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        setFragment();

    }

    private void setFragment(){
        mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.center_layout,mainFragment);
        transaction.commit();
    }
}
