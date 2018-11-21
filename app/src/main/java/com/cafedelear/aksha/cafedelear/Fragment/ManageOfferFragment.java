package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cafedelear.aksha.cafedelear.Adapter.ViewPagerAdapter;
import com.cafedelear.aksha.cafedelear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageOfferFragment extends Fragment {

    TabLayout offer_tab;
    ViewPager offer_pager;
    ViewPagerAdapter adapter;

    public ManageOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_offer, container, false);

        offer_tab = view.findViewById(R.id.offer_tab);
        offer_pager = view.findViewById(R.id.offer_pager);


        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new CategoryOfferFragment(),"Category Offer");

        adapter.AddFragment(new ProductOfferFragment(),"Product Offer");

        offer_pager.setAdapter(adapter);

        offer_tab.setupWithViewPager(offer_pager);

        return view;
    }

}
