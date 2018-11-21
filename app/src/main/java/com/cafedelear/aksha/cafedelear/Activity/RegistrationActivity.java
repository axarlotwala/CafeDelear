package com.cafedelear.aksha.cafedelear.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Model.Delear_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {


    private EditText username, phoneno, email, password;
    private RadioGroup gender;
    private Button register;
    RadioButton radiobutton;
    Session session;

    /* private String Register_Delear_URL = "http://192.168.0.103/CafeResturant/Delear/Register_Delear.php";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username);
        phoneno = findViewById(R.id.phoneno);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.register);

        session = new Session(getApplicationContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreUserData();
            }
        });
    }

    private void StoreUserData() {


        final String name = username.getText().toString().trim();
        final String phone = phoneno.getText().toString().trim();
        final String emailid = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();

        int selectedID = gender.getCheckedRadioButtonId();

        radiobutton = findViewById(selectedID);

        final String val = radiobutton.getText().toString();


        if (name.isEmpty()) {
            username.setError("Name Require");
            username.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            phoneno.setError("phone Require");
            phoneno.requestFocus();
            return;
        }

        if (emailid.isEmpty()) {
            email.setError("email Require");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
            email.setError("Enter A Valid Email Address");
            email.requestFocus();
            return;
        }

        if (Password.isEmpty()) {
            password.setError("Password Require");
            password.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            password.setError("Password Must Be At list 6 char long");
            password.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Constant.Register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error")) {

                        /*Delear_model model = new Delear_model();
                                model.setDelear_id(jsonObject.getString("delear_id"));
                                model.setDelear_email(jsonObject.getString("delear_email"));
                                model.setDelear_name(jsonObject.getString("delear_name"));*/

                        if(session.getInstace(getApplicationContext()).getIsLogin()) {
                            Log.d("Check_Loin","Or Not"+session.getInstace(getApplicationContext()).getIsLogin());
                            Intent intent = new Intent(RegistrationActivity.this, Bottom_navigationActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {

                        onStop();
                        Toast.makeText(RegistrationActivity.this,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("delear_name", name);
                params.put("delear_phoneno", phone);
                params.put("delear_email", emailid);
                params.put("delear_password", Password);
                params.put("gender", val);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
/*
    private void ClearTextValue() {

        username.setText("");
        phoneno.setText("");
        email.setText("");
        password.setText("");
        gender.clearCheck();
    }*/
}
