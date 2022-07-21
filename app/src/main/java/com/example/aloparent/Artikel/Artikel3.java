package com.example.aloparent.Artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.R;
import com.example.aloparent.View.Artikel;

public class Artikel3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel3);
    }

    //imtemt ke halaman artikel 4
    public void backButton(View v){
        Intent intent = new Intent(Artikel3.this, Artikel.class);
        startActivity(intent);
    }
}