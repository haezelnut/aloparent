package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PanduanOrangTuaScreen extends AppCompatActivity {

    public void backFromPanduanOrtu(View v){
        Intent intent = new Intent(PanduanOrangTuaScreen.this, Kelas.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_orang_tua_screen);
    }
}