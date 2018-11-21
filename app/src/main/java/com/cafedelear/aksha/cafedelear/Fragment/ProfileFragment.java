package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cafedelear.aksha.cafedelear.Activity.LoginActivity;
import com.cafedelear.aksha.cafedelear.MainActivity;
import com.cafedelear.aksha.cafedelear.Model.Delear_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView get_id,get_email;
    Session session;
    Button logout;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        get_id = view.findViewById(R.id.get_id);
        get_email = view.findViewById(R.id.get_email);

        session = new Session(getActivity());

        logout = view.findViewById(R.id.logout);


        get_id.setText(session.getDELEAR_ID());
        get_email.setText(session.getDELEAR_EMAIL());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.clearAll();
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().finish();

            }
        });

        return  view;
    }




}
