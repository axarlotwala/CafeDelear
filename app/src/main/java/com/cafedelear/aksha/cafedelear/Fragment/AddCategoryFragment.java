package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.UUID;

import static android.support.v4.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCategoryFragment extends Fragment {

    private ImageView cat_image;
    private EditText image_name,cat_name;
    private Button btn_new,btn_choose;
    /*private static final String New_Cat_Url = "http://192.168.0.103/CafeResturant/Delear/New_Category.php";*/
    private Uri PATH;
    private Bitmap bitmap;
    private TextView tv_path;
    private Session session;
    private String delear_id;

    private static final int PICK_IMAGE_REQUEST = 10;

    public AddCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_category, container, false);

        cat_image = view.findViewById(R.id.cat_image);
        image_name = view.findViewById(R.id.image_name);
        cat_name = view.findViewById(R.id.cat_name);
        btn_new = view.findViewById(R.id.btn_new);
        btn_choose = view.findViewById(R.id.btn_choose);
        tv_path = view.findViewById(R.id.tv_path);

        session = new Session(getActivity());

        delear_id = session.getDELEAR_ID();
        categoryAdd();

        return view;
    }

    private void categoryAdd() {

        // choose image
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFileChooser();
            }
        });

        // upload image using services

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadnewCategory();
            }
        });
    }

    // using intent open file chooser option

    private void ShowFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);

    }

// show selected and path image in imageview
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PATH = data.getData();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),PATH);
            cat_image.setImageBitmap(bitmap);
            tv_path.setText("Path : " .concat(GetPath(PATH)));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //get correct path of image
    private String GetPath(Uri uri){

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

    private void UploadnewCategory() {

      String iname = image_name.getText().toString().trim();
      String cname = cat_name.getText().toString().trim();
      String filepath = GetPath(PATH);

      if (iname.isEmpty()){

          image_name.setError("Image Name IS Require");
          image_name.requestFocus();
          return;
      }

      if (cname.isEmpty()){
          cat_name.setError("Category Name Is Require");
          cat_name.requestFocus();
          return;
      }

      String upload_id = UUID.randomUUID().toString();

        try {
            try {
                new MultipartUploadRequest(getActivity(),upload_id,Constant.New_Category_url)
                        .addFileToUpload(filepath,"image")
                        .addParameter("name",iname)
                        .addParameter("cat_name",cname)
                        .addParameter("delear_id",delear_id)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(3)
                        .startUpload();

                ClearData();

            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();

            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void ClearData() {

        cat_image.setImageBitmap(null);
        image_name.setText("");
        cat_name.setText("");
        tv_path.setText("");
    }

}
