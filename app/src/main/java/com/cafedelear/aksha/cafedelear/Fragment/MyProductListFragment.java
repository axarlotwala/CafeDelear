package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.MenuAdapter;
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProductListFragment extends Fragment {

    private RecyclerView menu_lists;
    private List<Menu_model> menu_models;
    private String catid, delear_id;
    Session session;

    public MyProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myproduct_list, container, false);

        menu_lists = view.findViewById(R.id.menu_lists);


        session = new Session(getActivity());
        delear_id = session.getDELEAR_ID();

        show_menuList();


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        menu_lists.setLayoutManager(manager);

        menu_models = new ArrayList<>();

        /*menu_lists.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));*/
        return view;
    }

    /*private void show_menuList() {

        //  String menuList = "http://192.168.0.103/CafeResturant/Delear/Menu_List.php?delear_id=10793507385bb9e0715f41f5bb9e&&cat_id=1";

        Bundle bundle = getArguments();
        catid = bundle.getString("cat_id");

        Log.d("Cat_ID", "Category : " + catid);


       String url = "http://192.168.0.103/CafeResturant/Delear/Menu_List.php?delear_id="+delear_id+"&cat_id="+catid;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                JSONObject jsonObject = null;

                for (int i=0;i<response.length() ;i++){

                    try {
                        jsonObject = response.getJSONObject(i);

                        Menu_model menu_model = new Menu_model();
                        menu_model.setMenu_name(jsonObject.getString("menu_name"));
                        *//*menu_model.setMenu_url(jsonObject.getString("menu_url"));*//*
                        *//*menu_model.setCat_id(jsonObject.getString("cat_id"));
                        menu_model.setDelear_id(jsonObject.getString("delear_id"));*//*

                        menu_models.add(menu_model);

                        Log.d("All_Value","Value : "+response.getJSONArray(i));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                MenuAdapter menuAdapter = new MenuAdapter(getActivity(),menu_models);
                menu_lists.setAdapter(menuAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }*/


    private void show_menuList(){

        Bundle bundle = getArguments();
        catid = bundle.getString("cat_id");

        Log.d("Cat_ID", "Category : " + catid);


        String url = "http://192.168.0.103/CafeResturant/Delear/Menu_List.php?delear_id="+delear_id+"&cat_id="+catid;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i=0;i<array.length();i++){

                        JSONObject jsonObject = array.getJSONObject(i);

                        Menu_model menu_model = new Menu_model();
                        menu_model.setMenu_name(jsonObject.getString("menu_name"));
                        menu_model.setMenu_url(jsonObject.getString("menu_url"));

                        menu_models.add(menu_model);

                    }

                    MenuAdapter menuAdapter = new MenuAdapter(getActivity(),menu_models);
                    menu_lists.setAdapter(menuAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
