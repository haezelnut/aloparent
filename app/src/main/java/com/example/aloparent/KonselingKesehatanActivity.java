package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KonselingKesehatanActivity extends AppCompatActivity {

    //inetnt ke ahli kesehatan
    public void ahliKesehatan(View v){
        Intent intent = new Intent(KonselingKesehatanActivity.this, AhliKesehatan.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konseling_kesehatan);
    }
}