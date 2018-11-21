package com.cafedelear.aksha.cafedelear.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cafedelear.aksha.cafedelear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentLocationFragment extends Fragment {

    TextView path;
    Button get_location;
    LocationManager locationManager;
    LocationListener locationListener;

    public CurrentLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currentlocation, container, false);
        /*full_address = view.findViewById(R.id.full_address);*/
        get_location = view.findViewById(R.id.get_location);
        path = view.findViewById(R.id.path);


        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocationData();
            }
        });

        return view;
    }


    private void getLocationData() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                /*path.setText(latitude + "\n" + longitude);*/
                Log.v("TAGIES","Location"+latitude+longitude);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { }

            @Override
            public void onProviderEnabled(String provider) { }

            @Override
            public void onProviderDisabled(String provider) { }
        };

    }

}
