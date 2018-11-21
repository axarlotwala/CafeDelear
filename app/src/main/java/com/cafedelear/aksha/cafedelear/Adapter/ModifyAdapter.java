package com.cafedelear.aksha.cafedelear.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.cafedelear.aksha.cafedelear.Fragment.ModifyProductFragment;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.ViewHolder> {

    private Context context;
    private List<Category_model> models;
    private String cat_id;

    public ModifyAdapter(Context context, List<Category_model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_delete_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(models.get(position).getUrl()).into(holder.category_image);
        holder.category_name.setText(models.get(position).getCat_name());

        cat_id = models.get(position).getCat_id();

        holder.linear_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
                Fragment fragment = new ModifyProductFragment();
                appCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.Frame_container,fragment)
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(context,models.get(position).getCat_id(),Toast.LENGTH_SHORT).show();


            }
        });


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateCategory();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteData();
            }
        });



    }

    private void UpdateCategory() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.single_category,null);
        final EditText update_catename = view.findViewById(R.id.update_catname);


        Button btn_update = view.findViewById(R.id.btn_update);

        builder.setTitle("Update Category");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = update_catename.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Update_Category_Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(context,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();

                        params.put("cat_id",cat_id);
                        params.put("cat_name",name);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);
            }
        });

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    private void DeleteData() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Delete_Category_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error")){

                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Toast.makeText(context,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params.put("cat_id",cat_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView category_image;
        TextView category_name,edit,delete;
        LinearLayout linear_modify;


        public ViewHolder(View itemView) {
            super(itemView);


            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            linear_modify = itemView.findViewById(R.id.linear_modify);

        }
    }
}
