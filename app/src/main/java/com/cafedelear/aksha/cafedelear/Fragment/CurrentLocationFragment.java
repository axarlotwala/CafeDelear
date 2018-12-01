package com.cafedelear.aksha.cafedelear.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.cafedelear.aksha.cafedelear.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentLocationFragment extends Fragment {


    Button update_request, remove_request;
    TextView loc_text;

    double longi;

    public CurrentLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_currentlocation, container, false);
        /*full_address = view.findViewById(R.id.full_address);*/
        update_request = view.findViewById(R.id.update_request);
        remove_request = view.findViewById(R.id.remove_request);
        loc_text = view.findViewById(R.id.loc_text);


        update_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateLocationRequest();
            }
        });

        remove_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveLocationRequest();
            }
        });

        return view;
    }



    @SuppressLint("MissingPermission")
    private void CreateLocationRequest() {


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        /*boolean checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);*/


           /* if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }*/



            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    String lati = Double.toString(location.getLatitude());
                    String longi = Double.toString(location.getLongitude());

                    loc_text.setText("" + lati + "--" + longi);

                    Log.d("Latitude","Location :"+location.getLatitude());
                    Log.d("Longitude","Location :"+location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) { }

                @Override
                public void onProviderEnabled(String provider) { }

                @Override
                public void onProviderDisabled(String provider) { }
            };

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }


    private void RemoveLocationRequest(){



    }


}
