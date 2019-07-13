package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cafedelear.aksha.cafedelear.R;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddLocationFragment extends Fragment {


    /*Add new branch address and convert address into reveese geocoding */
    /*make Form for get address detail and convert into lat lng
     * https://stackoverflow.com/questions/3574644/how-can-i-find-the-latitude-and-longitude-from-address*/

    public AddLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addlocation, container, false);

        return view;
    }
}
