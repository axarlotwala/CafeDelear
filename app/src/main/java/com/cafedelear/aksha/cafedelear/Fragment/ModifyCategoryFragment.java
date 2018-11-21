package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.CategoryAdapter;
import com.cafedelear.aksha.cafedelear.Adapter.ModifyAdapter;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
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
public class ModifyCategoryFragment extends Fragment {

    private List<Category_model> models;
    private RecyclerView modify_recycler;
    private SwipeRefreshLayout swipe;


    public ModifyCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modify_category, container, false);

        modify_recycler = view.findViewById(R.id.modify_recycler);
        swipe = view.findViewById(R.id.swipe);
        models = new ArrayList<>();

        modifyData();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipe.setRefreshing(false);

            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        modify_recycler.setLayoutManager(manager);



        return view;
    }

    private void modifyData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.All_Category_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);

                        Category_model categoryModel = new Category_model();
                        categoryModel.setCat_id(jsonObject.getString("cat_id"));
                        categoryModel.setUrl(jsonObject.getString("url"));
                        categoryModel.setCat_name(jsonObject.getString("cat_name"));

                        models.add(categoryModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ModifyAdapter modifyAdapter = new ModifyAdapter(getActivity(),models);
                    modify_recycler.setAdapter(modifyAdapter);


                }

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
