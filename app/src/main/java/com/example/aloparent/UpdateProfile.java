package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdateProfile extends AppCompatActivity {

    Button btnSimpan;
    Dialog dialog;

    public void backFormUbahData(View v){
        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }
}