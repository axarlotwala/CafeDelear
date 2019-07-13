package com.cafedelear.aksha.cafedelear.Activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.Fragment.HomeFragment;
import com.cafedelear.aksha.cafedelear.Fragment.MapBranchFragment;
import com.cafedelear.aksha.cafedelear.Fragment.NotificationFragment;
import com.cafedelear.aksha.cafedelear.Fragment.PaymentDetailFragment;
import com.cafedelear.aksha.cafedelear.Fragment.ProfileFragment;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.ConnectionBroadcast;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

public class Bottom_navigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static SpeedDialView userOption;
    private boolean isEnable = false;
    public static TextView tvCount;
    private ConnectionBroadcast broadcast = new ConnectionBroadcast();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new HomeFragment()).commit();
                    userOption.setVisibility(View.VISIBLE);
                    return true;

                    case R.id.nav_payment:
                        userOption.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new PaymentDetailFragment()).commit();
                        return true;

                case R.id.nav_notification:
                    userOption.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new MapBranchFragment()).commit();
                    return true;

                case R.id.nav_profile:
                    userOption.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new ProfileFragment()).commit();
                    return true;


            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        userOption = findViewById(R.id.userOption);
        userOption.inflate(R.menu.fab_menu);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.nav_home);
     //   BottomHelper.disableShiftMode(navigation);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        View view = menuView.getChildAt(1);
        BottomNavigationItemView itemView = (BottomNavigationItemView) view;
        View badge = LayoutInflater.from(this).inflate(R.layout.notification_badge,itemView,true);
        tvCount = badge.findViewById(R.id.tvCount);
        tvCount.setText("0");
//        itemView.addView(badge);
        /*userOption.addActionItem(new SpeedDialActionItem.Builder(R.id.nav_user, R.drawable.ic_blue_arrow)
                .create());*/

        userOption.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {

                switch (actionItem.getId()){

                    case R.id.nav_user :

                        return false;

                    case R.id.nav_cafeDelear :

                        return false;

                    case R.id.nav_rider :

                        return false;

                    default:
                        return false;
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcast,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcast);
    }

    @Override
    public void onBackPressed() {
       int fragment = getSupportFragmentManager().getBackStackEntryCount();
        if (fragment == 1){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
