package com.cafedelear.aksha.cafedelear.Fragment;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.Menu_Spinner_Adapter;
import com.cafedelear.aksha.cafedelear.Adapter.Spinner_adapter;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
import com.cafedelear.aksha.cafedelear.Model.Menu_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductOfferFragment extends Fragment {

    private AppCompatSpinner cat_spinn, menu_spinn;
    private ArrayList<Category_model> category_models;
    private ArrayList<Menu_model> menu_models;
    private String delearid, catid, menuid;
    Session session;
    private Button startoffer_date,endoffer_date,save_offer;
    private int myear, dmonth, mdate;
    DatePickerDialog dialog;
    EditText offer_value,start_date,end_date;


    public ProductOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_offer, container, false);
        cat_spinn = view.findViewById(R.id.cat_spinn);
        menu_spinn = view.findViewById(R.id.menu_spinn);
        startoffer_date = view.findViewById(R.id.startoffer_date);
        start_date = view.findViewById(R.id.start_date);

        endoffer_date = view.findViewById(R.id.endoffer_date);
        end_date = view.findViewById(R.id.end_date);

        save_offer = view.findViewById(R.id.save_offer);
        offer_value = view.findViewById(R.id.offer_value);



        /*dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); */// for show only present and future date
        /*date = DateFormat.getDateInstance().format("yyyy-MM-dd HH:mm:ss");*/

        /*category*/
        category_models = new ArrayList<>();
        pass_Spinn_Category();

        /*menu item*/
        menu_models = new ArrayList<>();
        pass_Spinn_Menu();

        session = new Session(getActivity());

        delearid = session.getDELEAR_ID();


        /*menu Spinner Data*/

        menu_spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Menu_model menu_model = (Menu_model) parent.getItemAtPosition(position);
                menuid = menu_model.getMenu_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*Category Spinner Data*/

        cat_spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Category_model categoryModel = (Category_model) parent.getItemAtPosition(position);
                catid = categoryModel.getCat_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*date picker dialog disable pastdate*/
        /*startoffer_date.setMinDate(System.currentTimeMillis() - 1000);*/ //only use if datepicker choose

        startoffer_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerStart();
            }
        });


        endoffer_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerEnd();
            }
        });

        save_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOffer();
            }
        });

        return view;
    }

    private void pass_Spinn_Category() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.All_Category_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Category_model model = new Category_model();
                        model.setCat_id(jsonObject.getString("cat_id"));
                        model.setCat_name(jsonObject.getString("cat_name"));
                        model.setUrl(jsonObject.getString("url"));

                        category_models.add(model);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                Spinner_adapter adapter = new Spinner_adapter(getActivity(), category_models);
                cat_spinn.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    private void pass_Spinn_Menu() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.Single_Menu_List_Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Menu_model menu_model = new Menu_model();
                        menu_model.setMenu_name(jsonObject.getString("menu_name"));
                        menu_model.setMenu_url(jsonObject.getString("menu_url"));
                        menu_model.setMenu_id(jsonObject.getString("menu_id"));

                        menu_models.add(menu_model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Menu_Spinner_Adapter adapter = new Menu_Spinner_Adapter(getActivity(), menu_models);
                menu_spinn.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }


    /*method of start offer*/

    private void offerStart() {


        final Calendar calendar = Calendar.getInstance();
        myear = calendar.get(Calendar.YEAR);
        dmonth = calendar.get(Calendar.MONTH);
        mdate = calendar.get(Calendar.DAY_OF_MONTH);

        dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                start_date.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            }
        },myear,dmonth,mdate);
        dialog.show();

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
    }

    private void offerEnd(){

        final Calendar calendar = Calendar.getInstance();
        myear = calendar.get(Calendar.YEAR);
        dmonth = calendar.get(Calendar.MONTH);
        mdate = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.roll(Calendar.DATE,1);


        dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                end_date.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            }
        },myear,dmonth,mdate);
        dialog.show();

        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
    }

    private void addOffer(){

        final String value = offer_value.getText().toString().trim();
        final String stdate = start_date.getText().toString().trim();
        final String ettime = end_date.getText().toString().trim();

        if (value.isEmpty()){

            offer_value.setError("Enter Value of Offer");
            offer_value.requestFocus();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Add_Offer_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("delear_id",delearid);
                params.put("cat_id",catid);
                params.put("menu_id",menuid);
                params.put("discount",value);
                params.put("start_time",stdate);
                params.put("end_time",ettime);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


}