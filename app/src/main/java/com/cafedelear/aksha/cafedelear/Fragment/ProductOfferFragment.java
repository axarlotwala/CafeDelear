package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cafedelear.aksha.cafedelear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductOfferFragment extends Fragment {


    public ProductOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_offer, container, false);
    }

}
