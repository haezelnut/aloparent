package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LupaPassword extends AppCompatActivity {

    public void backFromLupaPw(View v){
        Intent intent = new Intent(LupaPassword.this, LoginScreen.class);
        startActivity(intent);
    }
    public void btnKirimLupaPw(View v){
        Intent intent = new Intent(LupaPassword.this, LupaPasswordNext.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
    }
}