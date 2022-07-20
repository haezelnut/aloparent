package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.Artikel.Artikel1;
import com.example.aloparent.Artikel.Artikel2;
import com.example.aloparent.Artikel.Artikel3;
import com.example.aloparent.Artikel.Artikel4;
import com.example.aloparent.R;

public class Artikel extends AppCompatActivity {

    public void backFromArtikel(View v){
        Intent intent = new Intent(Artikel.this, Home.class);
        startActivity(intent);
    }

    public void artikel1(View v){
        Intent intent = new Intent(Artikel.this, Artikel1.class);
    }
    public void artikel2(View v){
        Intent intent = new Intent(Artikel.this, Artikel2.class);
    }
    public void artikel3(View v){
        Intent intent = new Intent(Artikel.this, Artikel3.class);
    }
    public void artikel4(View v){
        Intent intent = new Intent(Artikel.this, Artikel4.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
    }
}