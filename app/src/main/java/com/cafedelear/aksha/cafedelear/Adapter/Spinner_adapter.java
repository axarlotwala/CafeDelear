package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
import com.cafedelear.aksha.cafedelear.R;

import java.util.ArrayList;
import java.util.List;

public class Spinner_adapter extends ArrayAdapter<Category_model>  {

    public Spinner_adapter(Context context,ArrayList<Category_model> category_models){
        super(context,0,category_models);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    private View InitView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_fill,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.view_catImage);
        TextView id = convertView.findViewById(R.id.tv_category_id);
        TextView name = convertView.findViewById(R.id.tv_category);

        Category_model categoryModel = getItem(position);

        if (categoryModel != null) {

            Glide.with(getContext()).load(categoryModel.getUrl()).into(imageView);
            id.setText(categoryModel.getCat_id());
            name.setText(categoryModel.getCat_name());
        }
        return convertView;

    }
}
