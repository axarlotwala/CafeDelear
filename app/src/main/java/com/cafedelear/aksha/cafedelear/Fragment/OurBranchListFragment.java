package com.cafedelear.aksha.cafedelear.Fragment;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cafedelear.aksha.cafedelear.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class OurBranchListFragment extends Fragment {

    /*TextView address;
    Geocoder geocoder;
    List<Address> addresses;

    double lat = 21.224306;
    double log = 72.8308891;*/


    public OurBranchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ourbranchlist, container, false);

        return view;
    }

}
