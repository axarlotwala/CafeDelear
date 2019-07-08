package com.cafedelear.aksha.cafedelear.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.Fragment.HomeFragment;
import com.cafedelear.aksha.cafedelear.Fragment.NotificationFragment;
import com.cafedelear.aksha.cafedelear.Fragment.OurServicesFragment;
import com.cafedelear.aksha.cafedelear.Fragment.PaymentDetailFragment;
import com.cafedelear.aksha.cafedelear.Fragment.ProfileFragment;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.BottomHelper;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

public class Bottom_navigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static SpeedDialView userOption;
    private boolean isEnable = false;



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

                    case R.id.nav_job:
                        userOption.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new OurServicesFragment()).commit();
                    return true;

                case R.id.nav_notification:
                    userOption.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new NotificationFragment()).commit();
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
        BottomHelper.disableShiftMode(navigation);

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
    public void onBackPressed() {
       int fragment = getSupportFragmentManager().getBackStackEntryCount();
        if (fragment == 1){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
