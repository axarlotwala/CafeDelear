package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookTableFragment extends Fragment{

    private EditText name;
    private ImageView up_image;
    private Button select,upload;
    private static final int PICK_IMAGE = 10;
    private Uri uri;
    Bitmap bitmap;

    Session session;


    public BookTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_booktable, container, false);

        session = new Session(getActivity());

        up_image = view.findViewById(R.id.up_image);
        name = view.findViewById(R.id.name);
        upload = view.findViewById(R.id.upload);
        select = view.findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageUpload();
            }
        });


        return view;

    }


    private void showChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
            up_image.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String getPath(Uri pathuri) {

        String result;

        Cursor cursor = getActivity().getContentResolver().query(pathuri,null,null,null,null);

        if (cursor == null){
            result  = pathuri.getPath();
        } else {

            cursor.moveToFirst();
            int id = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result  = cursor.getString(id);
            cursor.close();
        }

        return result;
    }

    private void ImageUpload() {

        final String filepath = getPath(uri);

        final String iname = name.getText().toString();

        String upload_id = UUID.randomUUID().toString();



        try {
            new MultipartUploadRequest(getActivity(),upload_id,Constant.Temp_Image)
                    .addFileToUpload(filepath,"image")
                    .addParameter("name",iname)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

            Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    /*private String ImageToString(Bitmap bitmappath){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmappath.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebyte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagebyte,Base64.DEFAULT);
    }*/
}
