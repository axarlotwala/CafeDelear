package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.Model.Days_model;
import com.cafedelear.aksha.cafedelear.R;

import java.util.ArrayList;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.Holder> {

    private Context context;
    private ArrayList<Days_model> days_models;

    public DaysAdapter(Context context, ArrayList<Days_model> days_models) {
        this.context = context;
        this.days_models = days_models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_days,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        holder.check_dayName.setText(days_models.get(i).getDayName());
        holder.tv_startTime.setText(days_models.get(i).getStartTime());
        holder.tv_endTime.setText(days_models.get(i).getEndTime());

    }

    @Override
    public int getItemCount() {
        return days_models.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        CheckBox check_dayName;
        TextView tv_startTime,tv_endTime;


        public Holder(@NonNull View itemView) {
            super(itemView);

            check_dayName = itemView.findViewById(R.id.check_dayName);
            tv_startTime = itemView.findViewById(R.id.tv_startTime);
            tv_endTime = itemView.findViewById(R.id.tv_endTime);
        }
    }
}
