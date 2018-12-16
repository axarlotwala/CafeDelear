package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cafedelear.aksha.cafedelear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

   private LinearLayout category_card,order_card,offer_card,complain_card,timer_card,location;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        category_card = view.findViewById(R.id.category_card);
        order_card = view.findViewById(R.id.order_card);
        offer_card = view.findViewById(R.id.offer_card);
        complain_card = view.findViewById(R.id.complain_card);
        timer_card = view.findViewById(R.id.time_card);
        location = view.findViewById(R.id.location);

        AllClickEvents();



        return view;
    }

    private void AllClickEvents() {

        category_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("card_click","click");
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Frame_container,new ManageCategoryFragment());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Frame_container,new LocationFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        offer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.Frame_container,new ManageOfferFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }

}
