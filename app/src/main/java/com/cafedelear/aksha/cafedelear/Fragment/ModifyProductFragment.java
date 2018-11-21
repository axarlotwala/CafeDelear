package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.ModifyProductAdapter;
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyProductFragment extends Fragment {

    private RecyclerView modify_product;
    private List<Menu_model> menu_models;


    public ModifyProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_modify_product, container, false);

        modify_product = view.findViewById(R.id.modify_product);
        menu_models = new ArrayList<>();


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        modify_product.setLayoutManager(manager);

        ModifyMenu();

        return view;
    }

    private void ModifyMenu() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.Single_Menu_List_Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i=0;i <response.length() ; i++) {

                    try {
                        jsonObject = response.getJSONObject(i);

                        Menu_model menu_model = new Menu_model();
                        menu_model.setMenu_name(jsonObject.getString("menu_name"));
                        menu_model.setMenu_url(jsonObject.getString("menu_url"));

                        menu_models.add(menu_model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }


                ModifyProductAdapter modifyProductAdapter  = new ModifyProductAdapter(getActivity(),menu_models);
                modify_product.setAdapter(modifyProductAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

}