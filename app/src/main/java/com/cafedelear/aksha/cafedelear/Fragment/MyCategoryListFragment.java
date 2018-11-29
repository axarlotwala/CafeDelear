package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.CategoryAdapter;
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
public class MyCategoryListFragment extends Fragment {

    private RecyclerView category_recycler;
    List<Category_model> models;
    private SwipeRefreshLayout refresh;
    private String delear_id;
    Session session;

    public MyCategoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mycategorylist, container, false);

        /*Only View My Products & Category*/

        category_recycler = view.findViewById(R.id.category_recycler);
        refresh = view.findViewById(R.id.refresh);
        models = new ArrayList<>();

        session = new Session(getActivity());

        delear_id = session.getDELEAR_ID();

        Log.d("Check_Value","Delear_ID : "+delear_id);

        setCategory();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        category_recycler.setLayoutManager(manager);

        category_recycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;
    }

    private void setCategory() {

        //String Cat_url = "http://192.168.0.103/CafeResturant/Delear/All_Category.php?delear_id=+delear_id+";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.All_Category_url+delear_id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0 ;i <response.length() ; i++ ){
                    try {
                        jsonObject = response.getJSONObject(i);

                        Category_model categoryModel = new Category_model();
                        categoryModel.setCat_id(jsonObject.getString("cat_id"));
                        categoryModel.setCat_name(jsonObject.getString("cat_name"));
                        categoryModel.setUrl(jsonObject.getString("url"));
                        /*categoryModel.setDelear_id(jsonObject.getString("delear_id"));*/

                        /*Toast.makeText(getActivity(),jsonObject.getString("delear_id"), Toast.LENGTH_SHORT).show();*/
                        models.add(categoryModel);

                    } catch (JSONException e) {
                        e.printStackTrace();

                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                CategoryAdapter adapter = new CategoryAdapter(getActivity(),models);
                category_recycler.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);

    }

}
