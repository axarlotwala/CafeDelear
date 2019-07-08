package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cafedelear.aksha.cafedelear.Adapter.DaysAdapter;
import com.cafedelear.aksha.cafedelear.Model.Days_model;
import com.cafedelear.aksha.cafedelear.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimerFragment extends Fragment {


    private RecyclerView listDays;
    private ArrayList<Days_model> days_model;


    public SetTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_set_timer, container, false);
        listDays = view.findViewById(R.id.listDays);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        listDays.setLayoutManager(manager);

        setBroadCastTimer();

        DaysAdapter daysAdapter = new DaysAdapter(getActivity(),days_model);
        listDays.setAdapter(daysAdapter);

        return view;
    }

    private void setBroadCastTimer() {

        days_model = new ArrayList<>();

        Days_model day_model = new Days_model("1","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model);

        Days_model day_model1 = new Days_model("2","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model1);

        Days_model day_model2 = new Days_model("3","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model2);

        Days_model day_model3 = new Days_model("4","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model3);

        Days_model day_model4 = new Days_model("5","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model4);

        Days_model day_model5 = new Days_model("6","Sunday","09:00:00","19:00:00",false);
        days_model.add(day_model5);

        Days_model day_model6 = new Days_model("7","Sunday","09:00:00","19:00:00",true);
        days_model.add(day_model6);
    }



}
