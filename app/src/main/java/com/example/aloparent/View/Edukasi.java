package com.example.aloparent.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aloparent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Edukasi extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.edukasi);


        ImageSlider imageSlider = findViewById(R.id.view_pager_edukasi);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agar di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video2,"A di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agra di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));

        imageSlider.setImageList(slideModels,true);

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