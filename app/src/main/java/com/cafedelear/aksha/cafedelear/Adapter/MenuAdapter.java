package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    private Context context;
    private List<Menu_model> menu_models;
    private String menu_id;


    public MenuAdapter(Context context, List<Menu_model> menu_models) {
        this.context = context;
        this.menu_models = menu_models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_row,parent,false);
        return new ViewHolder(view) ;
    }


    @Override
    public int getItemCount() {
        return menu_models.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.menu_name.setText(menu_models.get(position).getMenu_name());
        Glide.with(context).load(menu_models.get(position).getMenu_url()).into(holder.menu_image);
         holder.change_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                menu_id = menu_models.get(position).getMenu_id();
                 /*holder.change_switch.setSplitTrack();*/
                 /*holder.change_switch.setChecked(true)*/;

                if (holder.change_switch.isChecked()){

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Set_Menu_Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("is_visible",holder.change_switch.getTextOn().toString());
                        params.put("menu_id",menu_id);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

                }
                else {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Set_Menu_Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("menu_id",menu_id);
                            params.put("is_visible",holder.change_switch.getTextOff().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);

                }


            }
        });


        /*end bindview holdr*/
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView menu_image;
        private TextView menu_name;
        private SwitchCompat change_switch;



        public ViewHolder(View itemView) {
            super(itemView);


            menu_image = itemView.findViewById(R.id.category_image);
            menu_name = itemView.findViewById(R.id.category_name);
            change_switch = itemView.findViewById(R.id.change_switch);



        }
    }
}
