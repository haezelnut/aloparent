package com.example.aloparent.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aloparent.Artikel.Artikel1;
import com.example.aloparent.Artikel.Artikel2;
import com.example.aloparent.Artikel.Artikel3;
import com.example.aloparent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Edukasi extends AppCompatActivity {

    // tekan back navbar 2 kali untuk keluar aplikasi

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Sekali lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void toVideo(View v){
        Intent intent = new Intent(Edukasi.this, VideoHariIni.class);
        startActivity(intent);
    }

    public void artikel1(View v){
        Intent intent = new Intent(Edukasi.this, Artikel1.class);
        startActivity(intent);
    }

    public void artikel2(View v){
        Intent intent = new Intent(Edukasi.this, Artikel2.class);
        startActivity(intent);
    }

    public void artikel3(View v){
        Intent intent = new Intent(Edukasi.this, Artikel3.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.edukasi);


        //ImageSlider imageSlider = findViewById(R.id.view_pager_edukasi);

        //List<SlideModel> slideModels = new ArrayList<>();

        //slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agar di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        //slideModels.add(new SlideModel(R.drawable.contoh_gambar_video2,"A di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        //slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agra di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));

        //imageSlider.setImageList(slideModels,true);

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