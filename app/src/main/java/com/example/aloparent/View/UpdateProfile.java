package com.example.aloparent.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.aloparent.Function.VolleyMultipartRequest;
import com.example.aloparent.Function.VolleyMultipartRequest.DataPart;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aloparent.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {

    private Dialog dialog;
    private Bitmap bitmap;
    private String encodeImageString;

    private CircleImageView profile_picture;
    private AppCompatButton btn_TakePhoto, btnSimpan;
    private EditText inputUsername, inputEmailLogin, inputKataSandi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btnSimpan = findViewById(R.id.btnSimpanData);
        btn_TakePhoto = findViewById(R.id.btn_TakePhoto);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputKataSandi = findViewById(R.id.inputKataSandi);
        profile_picture = findViewById(R.id.profile_picture);

        btn_TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdateProfile.this)
                        .cropSquare()
                        .maxResultSize(1080,1080)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }

    private byte[] encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        try{
            InputStream inputStream = getContentResolver().openInputStream(uri);
            bitmap  = BitmapFactory.decodeStream(inputStream);
            profile_picture.setImageBitmap(bitmap);
            uploadBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void backFormUbahData(View v){
        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }

    public void btnSimpanData(View v){

        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }

    private void uploadBitmap(final Bitmap bitmap){
        System.out.println("Ini Awal Upload Bitmap");
        String url = "http://192.168.43.247:3000/users/updateProfile";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.PUT, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject object = new JSONObject(new String(response.data));
                    Toast.makeText(UpdateProfile.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("message","Ini Error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Got Error", ""+error.getMessage());
            }
        }){
            @Override
            protected Map<String, DataPart> getByteData(){
                Map<String, DataPart> params = new HashMap<>();
                long imageName = System.currentTimeMillis();
                params.put("image", new DataPart(imageName+".jpg",encodeBitmapImage(bitmap) ));
                System.out.println(params);
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}