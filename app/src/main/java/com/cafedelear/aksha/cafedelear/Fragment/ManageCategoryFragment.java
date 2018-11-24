package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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
public class ManageCategoryFragment extends Fragment {

    TabLayout tab_bar;
    ViewPager view_pager;
    ViewPagerAdapter adapter;
    BottomNavigationView navigationView;


    public ManageCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manage_category, container, false);

        tab_bar = view.findViewById(R.id.tab_bar);
        view_pager = view.findViewById(R.id.view_pager);

        adapter = new ViewPagerAdapter(getChildFragmentManager()); //stackoverflow solution

        adapter.AddFragment(new AddCategoryFragment(),"Add Category");

        adapter.AddFragment(new AddProductFragment(),"Add Product");

        adapter.AddFragment(new MyCategoryListFragment(),"All");

        adapter.AddFragment(new ModifyCategoryFragment(),"Modify");

        view_pager.setAdapter(adapter);

        tab_bar.setupWithViewPager(view_pager);

        return view;
    }

}
