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

import com.example.aloparent.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Konseling extends AppCompatActivity {

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


    //ke scren konsling kseshatan
    public void toKonselingKesehatan(View v){
        Intent intent = new Intent(Konseling.this, KonselingKesehatanActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konseling);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.konseling);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId()){
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.konseling:
                        return true;
                    case R.id.kelas:
                        startActivity(new Intent(getApplicationContext(), Kelas.class));
                        overridePendingTransition(0,0);
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