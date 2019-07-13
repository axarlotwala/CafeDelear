package com.cafedelear.aksha.cafedelear.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cafedelear.aksha.cafedelear.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapBranchFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mapbranch,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment  = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapBranch);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(false);
        map.setIndoorEnabled(false);
        map.setBuildingsEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng wotw = new LatLng(21.223819,72.8301334);
        map.addMarker(new MarkerOptions().position(wotw)
                .title("Walk of the Week"));
        map.moveCamera(CameraUpdateFactory.newLatLng(wotw));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }
}
