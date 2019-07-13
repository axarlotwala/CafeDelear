package com.cafedelear.aksha.cafedelear.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cafedelear.aksha.cafedelear.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import static com.cafedelear.aksha.cafedelear.Activity.Bottom_navigationActivity.tvCount;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentDetailFragment extends Fragment implements View.OnClickListener {

    ImageView imgExpand,imgAdd,imgRemove;
    ExpandableLayout expandLayout;
   // static int mint = 0;

    public PaymentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_payment_detail, container, false);
        imgExpand = view.findViewById(R.id.imgExpand);
        expandLayout = view.findViewById(R.id.expandLayout);
      /*  imgAdd = view.findViewById(R.id.imgAdd);
        imgRemove  = view.findViewById(R.id.imgRemove);*/

        /*imgAdd.setOnClickListener(this);
        imgRemove.setOnClickListener(this);*/

        imgExpand.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

         /*   case R.id.imgAdd :
                mint = mint+1;
                display(mint);
                break;

            case R.id.imgRemove :
                mint = mint-1;
                display(mint);
                break;*/

            case R.id.imgExpand :
                expandLayout.toggle();
                break;

        }
    }


    private void display(int mint){

        tvCount.setText(""+mint);
    }
}
