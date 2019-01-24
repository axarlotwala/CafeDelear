package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cafedelear.aksha.cafedelear.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetTimerFragment extends Fragment {


    private AppCompatImageView day_button;
    private SwitchButton set_delete;
    private TimePicker time_picker;
    private Button send,cancle;



    public SetTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_set_timer, container, false);
        day_button = view.findViewById(R.id.day_button);
        set_delete = view.findViewById(R.id.set_delete);
        time_picker = view.findViewById(R.id.time_picker);
        send = view.findViewById(R.id.send);
        cancle = view.findViewById(R.id.cancel);


        day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogs();
            }
        });

        return view;
    }

    private void showDialogs() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.select_layout,null);

        AppCompatImageView first,second,third,four;
        TextView once,daily,five_days,custom;

        first = view.findViewById(R.id.first);
        second = view.findViewById(R.id.second);
        third = view.findViewById(R.id.third);
        four = view.findViewById(R.id.four);

        once = view.findViewById(R.id.once);
        daily = view.findViewById(R.id.daily);
        five_days = view.findViewById(R.id.five_days);
        custom = view.findViewById(R.id.custom);


        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

    }

    private void set_timer(){

        Calendar calendar = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= 23) {
            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    time_picker.getHour(),
                    time_picker.getMinute(),
                    0);
            Log.d("Time",""+time_picker.getHour());
            Log.d("MINUTE",""+time_picker.getMinute());

        }else {
            calendar.set(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    time_picker.getCurrentHour(),
                    time_picker.getCurrentMinute(),
                    0);

        }
    }

}
