package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloparent.R;
import com.example.aloparent.SharedRefrence.SharedPrefManager;
import com.example.aloparent.SharedRefrence.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileScreen extends AppCompatActivity {

    private TextView inputNamaOrangTua, inputEmailLogin, kataSandi;
    private ImageView profile_picture;
    private String email;

    public void backFormProfile(View v){
        Intent intent = new Intent(ProfileScreen.this, Home.class);
        startActivity(intent);
    }

    public void btnEdit(View v){
        Intent intent = new Intent(ProfileScreen.this, UpdateProfile.class);
        startActivity(intent);
    }

    public void btnLogout(View v){
        // fungsi logut shared prefrence
        final SharedPrefManager prefManager = new SharedPrefManager(this);
        prefManager.userLogout();
        Intent intent = new Intent(ProfileScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        inputNamaOrangTua = findViewById(R.id.inputNamaOrangTua);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        kataSandi = findViewById(R.id.kataSandi);
        profile_picture = findViewById(R.id.profile_picture);

        //Get User Data From SharedPref
        final SharedPrefManager prefManager = new SharedPrefManager(this);
        UserModel user = prefManager.getUserLogin();
        String email = user.getUserMail(), username = user.getUserName(), password = user.getUserPassword(), image = user.getUserImage();

        //Set Text View Value
        inputNamaOrangTua.setText(username);
        inputEmailLogin.setText(email);
        kataSandi.setText(password);

    }
}