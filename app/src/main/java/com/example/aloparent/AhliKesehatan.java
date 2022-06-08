package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AhliKesehatan extends AppCompatActivity {

    public void backToKesehatan(View v){
        Intent intent = new Intent(AhliKesehatan.this, KonselingKesehatanActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahli_kesehatan);
    }
}