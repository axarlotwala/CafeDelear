package com.cafedelear.aksha.cafedelear.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.Fragment.HomeFragment;
import com.cafedelear.aksha.cafedelear.Fragment.JobpostFragment;
import com.cafedelear.aksha.cafedelear.Fragment.NotificationFragment;
import com.cafedelear.aksha.cafedelear.Fragment.OurServicesFragment;
import com.cafedelear.aksha.cafedelear.Fragment.PaymentDetailFragment;
import com.cafedelear.aksha.cafedelear.Fragment.ProfileFragment;
import com.cafedelear.aksha.cafedelear.MainActivity;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.BottomHelper;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

public class Bottom_navigationActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new HomeFragment()).commit();
                    return true;

                    case R.id.nav_payment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new PaymentDetailFragment()).commit();
                    return true;

                    case R.id.nav_job:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new OurServicesFragment()).commit();
                    return true;

                case R.id.nav_notification:
                    getSupportFragmentManager().beginTransaction().replace(R.id.Frame_container,new NotificationFragment()).commit();
                    return true;

                case R.id.nav_profile:
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.nav_home);
        BottomHelper.disableShiftMode(navigation);






    }

}
