package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LupaPasswordNext extends AppCompatActivity {

    public void backFromLupaPw2(View v){
        Intent intent = new Intent(LupaPasswordNext.this, LupaPassword.class);
        startActivity(intent);
    }

    public void btnLoginLupaPw(View v){
        Intent intent = new Intent(LupaPasswordNext.this, LoginScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password_next);
    }
}