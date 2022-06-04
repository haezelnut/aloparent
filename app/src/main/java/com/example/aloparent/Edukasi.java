package com.example.aloparent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.jar.Pack200;

public class Edukasi extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.kelas);

        ViewPager viewPager;
        ArrayList<Integer> images = new ArrayList<>();
        ViewPagerAdapter adapter2;

        viewPager = findViewById(R.id.view_pager1);

        images.add(R.drawable.contoh_gambar_video1);
        images.add(R.drawable.contoh_gambar_video2);

        adapter2 = new ViewPagerAdapter(this,images);
        viewPager.setPadding(0,0,0,0);

        viewPager.setAdapter(adapter2);

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
                        startActivity(new Intent(getApplicationContext(), Kelas.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.edukasi:
                        return true;

                }
                return false;
            }
        });
    }
}