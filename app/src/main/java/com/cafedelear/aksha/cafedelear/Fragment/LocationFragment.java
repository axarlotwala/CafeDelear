package com.cafedelear.aksha.cafedelear.Fragment;


import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cafedelear.aksha.cafedelear.Adapter.ViewPagerAdapter;
import com.cafedelear.aksha.cafedelear.R;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    private TabLayout tab_location;
    private ViewPager location_pager;
    ViewPagerAdapter adapter;


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        tab_location = view.findViewById(R.id.tab_location);
        location_pager = view.findViewById(R.id.location_pager);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new CurrentLocationFragment(),"Current Location");

        adapter.AddFragment(new OurBranchListFragment(),"Our Branch");

        location_pager.setAdapter(adapter);

        tab_location.setupWithViewPager(location_pager);

        return view;
    }

}
