package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.R;
import com.example.aloparent.SharedPrefManager;

public class ProfileScreen extends AppCompatActivity {


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
    }
}