package com.example.aloparent.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aloparent.Function.VolleyMultipartRequest;
import com.example.aloparent.R;
import com.example.aloparent.SharedRefrence.SharedPrefManager;
import com.example.aloparent.SharedRefrence.UserModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {

    private Bitmap bitmap;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int PICK_IMAGE_REQUEST =1 ;
    private String filePath;
    private String url = "http://192.168.43.247:3000/users/updateProfile";
    private CircleImageView profile_picture;
    private AppCompatButton btn_TakePhoto, btnSimpan;
    private EditText inputUsername, inputEmailLogin, inputKataSandi;
    private String email, username, password, image;

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

        //Get User Data From SharedPref
        final SharedPrefManager prefManager = new SharedPrefManager(this);
        UserModel user = prefManager.getUserLogin();
        email = user.getUserMail();
        username = user.getUserName();
        password = user.getUserPassword();
        image = user.getUserImage();

        inputUsername.setText(username);
        inputEmailLogin.setText(email);
        inputKataSandi.setText(password);

        btn_TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(UpdateProfile.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(UpdateProfile.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(UpdateProfile.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                } else {
                    Log.e("Else", "Else");
                    showFileChooser();
                }


            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void backFormUbahData(View v){
        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }

    public void btnSimpanData(View v){
        uploaddatatodb(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri picUri = data.getData();
            filePath = getPath(picUri);
            if (filePath != null) {
                try {

                    Log.d("filePath", String.valueOf(filePath));
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), picUri);
                    profile_picture.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(
                        UpdateProfile.this, "no image selected",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    private byte[] encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,40,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploaddatatodb(final Bitmap bitmap)
    {
        final String temp_name =inputUsername.getText().toString().trim();
        final String temp_password =inputKataSandi.getText().toString().trim();

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.PUT, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                getUserInfromation(email);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateProfile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Got Error : ",""+error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("username", temp_name);
                param.put("email", email);
                param.put("password", temp_password);
                return param;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> param = new HashMap<>();
                long imageName = System.currentTimeMillis();
                param.put("user_Image", new DataPart(imageName + ".jpg", encodeBitmapImage(bitmap)));
                return param;
            }
        };
        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    private void getUserInfromation(String email){
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        String URL = "http://192.168.43.247:3000/users/"+email;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            String username = (String) response.get("username");
                            String password = (String) response.get("password");
                            String userImage = (String) response.get("user_Image");


                            // menyimpan data user menggunakan shared refrence
                            final SharedPrefManager prefManager = new SharedPrefManager(getApplicationContext());
                            UserModel user = new UserModel(email,username,password,userImage);
                            prefManager.setUserLogin(user, true);

                            //Starting Home activity
                            Toast.makeText(UpdateProfile.this, "Profile Updated !!!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UpdateProfile.this, ProfileScreen.class);
                            startActivity(i);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue1.add(jsonObjectRequest);
    }

}