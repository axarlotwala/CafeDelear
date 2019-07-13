package com.cafedelear.aksha.cafedelear.Utlities;

import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cafedelear.aksha.cafedelear.Activity.Bottom_navigationActivity;
import com.cafedelear.aksha.cafedelear.R;

public class ConnectionBroadcast extends BroadcastReceiver {

    Dialog dialog;
    TextView tvConnection_status;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            View view  = LayoutInflater.from(context).inflate(R.layout.dialog_nointernet,null);
            dialog = new Dialog(context,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
            dialog.setContentView(view);

            if (!noConnectivity){
                dialog.dismiss();
            }else {
                dialog.show();

            }
        }

    }

}
