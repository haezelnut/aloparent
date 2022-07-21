package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.Artikel.Artikel1;
import com.example.aloparent.Artikel.Artikel2;
import com.example.aloparent.Artikel.Artikel3;
import com.example.aloparent.Artikel.Artikel4;
import com.example.aloparent.R;

public class Artikel extends AppCompatActivity {

    private CardView artikel1,artikel2,artikel3,artikel4;

    public void backFromArtikel(View v){
        Intent intent = new Intent(Artikel.this, Home.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        artikel1 = findViewById(R.id.artikel1);
        artikel2 = findViewById(R.id.artikel2);
        artikel3 = findViewById(R.id.artikel3);
        artikel4 = findViewById(R.id.artikel4);

        artikel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Artikel.this, Artikel1.class);
                startActivity(intent);
            }
        });

        artikel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Artikel.this, Artikel2.class);
                startActivity(intent);
            }
        });

        artikel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Artikel.this, Artikel3.class);
                startActivity(intent);
            }
        });

        artikel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Artikel.this, Artikel4.class);
                startActivity(intent);
            }
        });

    }
}