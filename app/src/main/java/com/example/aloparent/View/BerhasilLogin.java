package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.R;

public class BerhasilLogin extends AppCompatActivity {

    public void toHome1(View v){
        Intent intent = new Intent(BerhasilLogin.this, Home.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_login);
    }
}