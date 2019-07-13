package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cafedelear.aksha.cafedelear.Adapter.ViewPagerAdapter;
import com.cafedelear.aksha.cafedelear.R;
import com.google.android.material.tabs.TabLayout;

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

        adapter.AddFragment(new AddLocationFragment(),"New Branch");

        adapter.AddFragment(new MapBranchFragment(),"Our Branch");

        location_pager.setAdapter(adapter);

        tab_location.setupWithViewPager(location_pager);

        return view;
    }

}
