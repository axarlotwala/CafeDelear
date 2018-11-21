package com.cafedelear.aksha.cafedelear.Activity;

import android.app.ProgressDialog;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private TextView new_user;
    private EditText login_email, login_password;
    private Button login;
    private Session session;
    /* private final String Login_URL = "http://192.168.43.69/CafeResturant/Delear/Login_Delear.php";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        new_user = findViewById(R.id.new_User);

        session = new Session(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void userLogin() {

        final String email = login_email.getText().toString().trim();
        final String password = login_password.getText().toString().trim();

        if (email.isEmpty()) {
            login_email.setError("Email Require");
            login_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_email.setError("Enter A Valid Email Address");
            login_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            login_password.setError("password is Empty");
            login_password.requestFocus();
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "Please Wait...", "Loading", true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.Login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error")) {

                        Intent intent = new Intent(LoginActivity.this, Bottom_navigationActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        /*Delear_model model = new Delear_model(
                                jsonObject.getString("delear_id"),
                                jsonObject.getString("delear_name"),
                                jsonObject.getString("delear_email"));*/

                        String userid = jsonObject.getString("delear_id").toString();
                        String username = jsonObject.getString("delear_name").toString();
                        String useremail = jsonObject.getString("delear_email").toString();

                        session.setDELEAR_ID(userid);
                        session.setDELEAR_EMAIL(useremail);
                        session.setDELEAR_NAME(username);
                        session.setIsLogin(true);


                        Log.d("SetCheck", "set" + userid);

                        session.getInstace(getApplicationContext()).getIsLogin();
                        Log.d("DelearID", "Id :" + jsonObject.getString("delear_id"));
                        Log.d("DelearEmail", "Email : " + jsonObject.getString("delear_email"));
                        Log.d("Delear_name", "Name" + jsonObject.getString("delear_name"));
                        Log.d("IS_Login", "Check : " + session.getInstace(getApplicationContext()).getIsLogin());
                        Log.d("Get_Email", "Get :" + session.getDELEAR_EMAIL());

                    } else {
                        dialog.dismiss();
                        onStop();
                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("delear_email", email);
                params.put("delear_password", password);


                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }


}
