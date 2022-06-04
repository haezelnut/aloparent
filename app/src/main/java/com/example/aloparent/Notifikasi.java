package com.example.aloparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Notifikasi extends AppCompatActivity {

    // intent kembali ke home
    public void backFromNotifikasi(View v){
        Intent intent = new Intent(Notifikasi.this, Home.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifikasi);
    }
}
