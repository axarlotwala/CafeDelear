package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;

import java.util.ArrayList;

public class Menu_Spinner_Adapter extends ArrayAdapter<Menu_model> {

    String menuid,catid;

    public Menu_Spinner_Adapter(@NonNull Context context, ArrayList<Menu_model> menu_models) {
        super(context, 0,menu_models);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position, convertView, parent);
    }



    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position, convertView, parent);
    }

    private View InitView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_spinner,parent,false);
        }

        ImageView menu_image = convertView.findViewById(R.id.view_menuImage);
        TextView menu_name = convertView.findViewById(R.id.tv_menuname);
        /*TextView menu_id = convertView.findViewById(R.id.tv_category_id);*/


        Menu_model menu_model = getItem(position);

        if (menu_model != null){
            Glide.with(getContext()).load(menu_model.getMenu_url()).into(menu_image);
            menuid = menu_model.getMenu_id();
            catid = menu_model.getCat_id();
            menu_name.setText(menu_model.getMenu_name());
        }
        return convertView;
    }
}
