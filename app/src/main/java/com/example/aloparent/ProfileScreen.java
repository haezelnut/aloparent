package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileScreen extends AppCompatActivity {

    public void backFormProfile(View v){
        Intent intent = new Intent(ProfileScreen.this, Home.class);
        startActivity(intent);
    }

    public void btnUbahData(View v){
        Intent intent = new Intent(ProfileScreen.this, UpdateProfile.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
    }
}