package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cafedelear.aksha.cafedelear.Model.ServicesModel;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private Context context;
    private List<ServicesModel> servicesModels;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "CafeDelear";


    public ServicesAdapter(Context context, List<ServicesModel> servicesModels) {
        this.context = context;
        this.servicesModels = servicesModels;

        sharedPreferences = context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_row_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.checked_value.setText(servicesModels.get(i).getServices_name());
        viewHolder.checked_value.getText().toString();
    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatCheckBox checked_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checked_value = itemView.findViewById(R.id.checked_value);
        }
    }
}
