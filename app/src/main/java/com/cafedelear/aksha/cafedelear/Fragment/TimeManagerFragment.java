package com.cafedelear.aksha.cafedelear.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeManagerFragment extends Fragment {

    private FloatingActionButton float_action;
    private TextView dates;


    public TimeManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_time_manager, container, false);

        float_action = view.findViewById(R.id.float_action);
        dates = view.findViewById(R.id.dates);

        SimpleDateFormat sim_format = new SimpleDateFormat("EEEE yyyy-MMM-dd");

        /*get next 5th day from today*/

        /*Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,5);
        date = calendar.getTime();

        Log.d("NEXT_DAT",":"+date);
        dates.setText(date.toString());*/

        /*get list of 5th day from today*/
        for (int i=0;i<7;i++){

            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE,i);
           // String day = format.format(calendar.getTime());
            //dates.setText(sim_format.format(calendar.getTime()));
            String days = sim_format.format(calendar.getTime());
            Log.d("Days",":"+days);
        }




        /*get Current Day in string*/
        /*Date date = new Date();
        dates.setText(format.format(date));*/


        float_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Frame_container,new SetTimerFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }



}
