package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.cafedelear.aksha.cafedelear.Adapter.CategoryAdapter;
import com.cafedelear.aksha.cafedelear.Adapter.ModifyAdapter;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
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
public class ModifyCategoryFragment extends Fragment {

    private List<Category_model> models;
    private RecyclerView modify_recycler;

    String delear_id;
    Session session;


    public ModifyCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modify_category, container, false);

        modify_recycler = view.findViewById(R.id.modify_recycler);

        session = new Session(getActivity());
        models = new ArrayList<>();

        modifyData();


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        modify_recycler.setLayoutManager(manager);

        delear_id = session.getDELEAR_ID();

        return view;
    }

    private void modifyData() {

        delear_id = session.getDELEAR_ID();

        /*String url = "http://192.168.0.103/CafeResturant/Delear/Category_List.php";*/



        JsonArrayRequest request = new JsonArrayRequest(Constant.All_Category_url+delear_id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);

                        Category_model categoryModel = new Category_model();
                        categoryModel.setCat_id(jsonObject.getString("cat_id"));
                        categoryModel.setCat_name(jsonObject.getString("cat_name"));
                        categoryModel.setUrl(jsonObject.getString("url"));

                        models.add(categoryModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                ModifyAdapter modifyAdapter = new ModifyAdapter(getActivity(),models);
                modify_recycler.setAdapter(modifyAdapter);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }

}