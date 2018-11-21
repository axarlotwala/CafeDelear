package com.cafedelear.aksha.cafedelear.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafedelear.aksha.cafedelear.Adapter.Spinner_adapter;
import com.cafedelear.aksha.cafedelear.Model.Category_model;
import com.cafedelear.aksha.cafedelear.Model.Delear_model;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Constant;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    private Spinner spinn_cat;
    private ArrayList<Category_model> category_models;
    Button btn_send,choose_image;
    private ImageView product_image;
    private Uri FILEPATH;
    private Bitmap bitmap;
    EditText menu_name,price,image_name;
    private TextView pathview;
    private SwipeRefreshLayout refresh;
    private static final int PICK_IMAGE_REQUEST = 20;
     Session session;
    String catId,delear_id;

    /*private static final String Spinn_Cat = "http://192.168.0.103/CafeResturant/Delear/Category_List.php";*/

    /*private static final String New_Product_URL = "http://192.168.0.103/CafeResturant/Delear/New_Product.php";*/


    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        spinn_cat = view.findViewById(R.id.spinn_cat);
        btn_send = view.findViewById(R.id.btn_send);
        choose_image = view.findViewById(R.id.choose_image);
        menu_name = view.findViewById(R.id.menu_name);
        image_name = view.findViewById(R.id.image_name);
        price = view.findViewById(R.id.price);
        product_image = view.findViewById(R.id.product_image);
        pathview = view.findViewById(R.id.pathview);
        session = new Session(getActivity());

        delear_id = session.getDELEAR_ID();

        category_models = new ArrayList<>();

        fiilData();

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreNewProduct();
            }
        });




        /*Selected Data Show In Top In Spinner*/
        spinn_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category_model click_model = (Category_model) parent.getItemAtPosition(position);
                catId = click_model.getCat_id();
                String name = click_model.getCat_name();
                String image = click_model.getUrl();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }


    /*image processing for select and get image path and view*/
    private void SelectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Product Photo"),PICK_IMAGE_REQUEST);

    }

    /*result of selected image*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FILEPATH = data.getData();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),FILEPATH);
            product_image.setImageBitmap(bitmap);
            pathview.setText("path : " .concat(GetImagePath(FILEPATH)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*get correctpath of images*/
    private String GetImagePath(Uri uri){

        String result;

        Cursor cursor = getActivity().getContentResolver().query(uri,null,null,null,null);

        if (cursor == null){
            result = uri.getPath();
        }
        else {

            cursor.moveToFirst();
            int id = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            result = cursor.getString(id);
            cursor.close();
        }

        return result;
    }


    /*Category Data View In spinner*/
    private void fiilData () {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constant.Category_Visible_url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {

                        try {
                            jsonObject = response.getJSONObject(i);
                            Category_model model = new Category_model();
                            model.setCat_id(jsonObject.getString("cat_id"));
                            model.setCat_name(jsonObject.getString("cat_name"));
                            model.setUrl(jsonObject.getString("url"));

                            category_models.add(model);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Spinner_adapter adapter = new Spinner_adapter(getActivity(), category_models);
                    spinn_cat.setAdapter(adapter);
                    spinn_cat.setPrompt("Select Category ");

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(jsonArrayRequest);

        }

       /*Get Data and Store Into Server USing Multipart depen*/
    private void StoreNewProduct() {

        String iname = image_name.getText().toString().trim();
        String mname = menu_name.getText().toString().trim();
        String pro_price = price.getText().toString().trim();
        String FilePath = GetImagePath(FILEPATH);

        if (iname.isEmpty()){
            image_name.setError("Require Image Name");
            image_name.requestFocus();
            return;
        }

        if (mname.isEmpty()){
            menu_name.setError("Require Menu Name");
            menu_name.requestFocus();
            return;
        }


        if (pro_price.isEmpty()){
            price.setError("Enter Product Price");
            price.requestFocus();
            return;
        }


        String uploadID = UUID.randomUUID().toString();

        try {
            try {
                new MultipartUploadRequest(getActivity(),uploadID,Constant.New_Product_url)
                        .addFileToUpload(FilePath,"image")
                        .addParameter("name",iname)
                        .addParameter("cat_id",catId)
                        .addParameter("menu_name",mname)
                        .addParameter("menu_price",pro_price)
                        .addParameter("delear_id",delear_id)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(3)
                        .startUpload();

                ClearValues();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void ClearValues() {

        product_image.setImageBitmap(null);
        image_name.setText("");
        pathview.setText("");
        menu_name.setText("");
        price.setText("");
    }


}



