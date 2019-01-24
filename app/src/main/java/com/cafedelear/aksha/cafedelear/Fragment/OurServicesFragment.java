package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.ServicesAdapter;
import com.cafedelear.aksha.cafedelear.Model.ServicesModel;
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
public class OurServicesFragment extends Fragment {

    private RecyclerView services_list;
    private List<ServicesModel> servicesModels;


    public OurServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_our_services, container, false);
        services_list = view.findViewById(R.id.services_list);

        setServices();

        servicesModels = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        services_list.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void setServices(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Services_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ServicesModel servicesModel = new ServicesModel();
                        servicesModel.setServices_name(jsonObject.getString("services_name"));
                        servicesModel.setServices_id(jsonObject.getString("services_id"));
                        servicesModels.add(servicesModel);
                    }


                    ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(),servicesModels);
                    services_list.setAdapter(servicesAdapter);

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

        RequestQueue requestQueue  = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
