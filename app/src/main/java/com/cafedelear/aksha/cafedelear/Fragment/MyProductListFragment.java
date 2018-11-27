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
    private String id;


    public MyProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myproduct_list, container, false);

        menu_lists = view.findViewById(R.id.menu_lists);
        menu_models = new ArrayList<>();


        show_menuList();

        Bundle bundle = getArguments();
        bundle.getString("cat_id");

        Log.d("Cat_ID", "Category : " + bundle.getString("cat_id"));

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        menu_lists.setLayoutManager(manager);

        /*menu_lists.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));*/
        return view;
    }

    private void show_menuList(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.Single_Menu_List_Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
                Log.d("Check_cat","Cat_List : "+response.toString());


                MenuAdapter menuAdapter = new MenuAdapter(getActivity(),menu_models);
                menu_lists.setAdapter(menuAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
