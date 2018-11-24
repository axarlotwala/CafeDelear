package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cafedelear.aksha.cafedelear.Activity.LoginActivity;
import com.cafedelear.aksha.cafedelear.MainActivity;
import com.cafedelear.aksha.cafedelear.Model.Delear_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView get_id;
    TextView get_email;
    String pid;
    Session session;
    Button logout;
    private static final int PICK_REQUEST = 10;
    private ImageView profile,change,profile_set;
    private Uri pathuri;
    private Bitmap bitmap;
    private String delear_id;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        get_id = view.findViewById(R.id.get_id);
        get_email = view.findViewById(R.id.get_email);
        profile = view.findViewById(R.id.profile);
        change = view.findViewById(R.id.change);
        profile_set = view.findViewById(R.id.profile_set);


        session = new Session(getActivity());

        delear_id = session.getDELEAR_ID();

        logout = view.findViewById(R.id.logout);


        get_id.setText(session.getDELEAR_ID());
        get_email.setText(session.getDELEAR_EMAIL());



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.clearAll();
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().finish();

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
                profile_set.setVisibility(View.VISIBLE);
                change.setVisibility(View.INVISIBLE);
            }
        });

        profile_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                change.setVisibility(View.VISIBLE);
                profile_set.setVisibility(View.INVISIBLE);

            }
        });


        Glide.with(getActivity()).load(session.getDELEAR_PROFILE()).into(profile);
        return  view;
    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select profile Photo"),PICK_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        pathuri = data.getData();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),pathuri);
            profile.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    private String getPath(Uri uri){

        String result;

        Cursor cursor = getActivity().getContentResolver().query(uri,null,null,null,null);

        if (cursor == null){
            result = uri.getPath();
        }else {

            cursor.moveToFirst();
            int id = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(id);
            cursor.close();
        }

        return result;
    }

    private void updateProfile() {

            final String filepath = getPath(pathuri);


            String upload_ID = UUID.randomUUID().toString();


        try {
            new MultipartUploadRequest(getActivity(),upload_ID,Constant.profile_Update)
                    .addFileToUpload(filepath,"image")
                    .addParameter("name","1")
                    .addParameter("delear_id",delear_id)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }


}
