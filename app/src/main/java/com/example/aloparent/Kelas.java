package com.example.aloparent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kelas extends AppCompatActivity {

    public void kePembelajaran1(View v){
        Intent intent = new Intent(Kelas.this, PanduanOrangTuaScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.kelas);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.konseling:
                        startActivity(new Intent(getApplicationContext(), Konseling.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.kelas:
                        return true;
                    case R.id.edukasi:
                        startActivity(new Intent(getApplicationContext(), Edukasi.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}