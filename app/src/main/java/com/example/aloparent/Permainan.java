package com.example.aloparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Permainan extends AppCompatActivity {

    public void backFromPermainan(View v){
        Intent intent = new Intent(Permainan.this, Home.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permainan);
    }
}
