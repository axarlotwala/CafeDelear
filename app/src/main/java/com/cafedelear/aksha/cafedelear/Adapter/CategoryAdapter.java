package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.cafedelear.aksha.cafedelear.Fragment.MyProductListFragment;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;
import com.kyleduo.switchbutton.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category_model> category_models;
    private String switch_value, cat;


    public CategoryAdapter(Context context, List<Category_model> category_models) {
        this.context = context;
        this.category_models = category_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcategory_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        /*holder.cat_id.setText(category_models.get(position).getCat_id());*/
        holder.category_name.setText(category_models.get(position).getCat_name());
        Glide.with(context).load(category_models.get(position).getUrl()).into(holder.category_image);

       //open product list set by category id if category visible then open product otherwise show toast

        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity appCompatActivity  = (AppCompatActivity) v.getContext();
                Fragment fragment = new MyProductListFragment();
                appCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Frame_container,fragment)
                        .addToBackStack(null)
                        .commit();




                Toast.makeText(context,category_models.get(position).getCat_id(), Toast.LENGTH_SHORT).show();
                Log.v("CAT_ID",category_models.get(position).getCat_id());

            }
        });


        holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                cat = category_models.get(position).getCat_id();


                if (holder.status.isChecked()) {

                    switch_value = holder.status.getTextOff().toString();



                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Set_Category_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("cat_visible", switch_value);
                            params.put("cat_id", cat);
                            return params;
                        }
                    };


                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);

                } else {

                    switch_value = holder.status.getTextOn().toString();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Set_Category_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("cat_visible", switch_value);
                            params.put("cat_id", cat);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);

                }

            }
        });


    }



    @Override
    public int getItemCount() {
        return category_models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView category_image;
        private TextView category_name;
        private SwitchButton status;
        private LinearLayout click;


        public ViewHolder(View itemView) {
            super(itemView);

            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
            status = itemView.findViewById(R.id.status);
            click = itemView.findViewById(R.id.click);

            /*cat_id = itemView.findViewById(R.id.cat_id);*/


        }
    }
}
