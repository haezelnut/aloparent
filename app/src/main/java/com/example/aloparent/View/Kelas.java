package com.example.aloparent.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aloparent.R;
import com.example.aloparent.SoalKelas.SoalA;
import com.example.aloparent.SoalKelas.SoalB;
import com.example.aloparent.SoalKelas.SoalC;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kelas extends AppCompatActivity {

    Dialog dialog;

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


    public void kePembelajaran1(View v){
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_panduan_orang_tua_screen);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton lanjut = dialog.findViewById(R.id.button);
        ImageButton back = dialog.findViewById(R.id.imageButton);

        dialog.show();

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kelas.this, SoalA.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

      //  Intent intent = new Intent(Kelas.this, PanduanOrangTuaScreen.class);
      // startActivity(intent);
    }

    public void toPembelajaran2(View v){
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_panduan_orang_tua_screen);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton lanjut = dialog.findViewById(R.id.button);
        ImageButton back = dialog.findViewById(R.id.imageButton);

        dialog.show();

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kelas.this, SoalB.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    public void kePembelajaran3(View v){
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_panduan_orang_tua_screen);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton lanjut = dialog.findViewById(R.id.button);
        ImageButton back = dialog.findViewById(R.id.imageButton);

        dialog.show();

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Kelas.this, SoalC.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
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