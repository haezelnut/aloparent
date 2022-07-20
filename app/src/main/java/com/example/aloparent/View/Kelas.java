package com.example.aloparent.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aloparent.R;
import com.example.aloparent.SharedRefrence.SharedPrefManager;
import com.example.aloparent.SharedRefrence.UserModel;
import com.example.aloparent.SoalKelas.SoalA;
import com.example.aloparent.SoalKelas.SoalB;
import com.example.aloparent.SoalKelas.SoalC;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Kelas extends AppCompatActivity {

    private Dialog dialog;
    private ProgressBar kelasA, kelasB, kelasC, pencapaianHariIni;
    private TextView txtPembelajaran1, txtPembelajaran2, txtPembelajaran3, jumlahPencapaian, tv_UserName;
    private CircleImageView foto_profil_anak;

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

        kelasA = findViewById(R.id.progess_pembelajaran_1);
        kelasB = findViewById(R.id.progess_pembelajaran_2);
        kelasC = findViewById(R.id.progess_pembelajaran_3);
        pencapaianHariIni = findViewById(R.id.progressPencapaian);

        txtPembelajaran1 = findViewById(R.id.textPembelajran1);
        txtPembelajaran2 = findViewById(R.id.textPembelajran2);
        txtPembelajaran3 = findViewById(R.id.textPembelajran3);
        jumlahPencapaian = findViewById(R.id.jumlah_pencapaian);
        tv_UserName = findViewById(R.id.tv_UserName);
        foto_profil_anak = findViewById(R.id.foto_profil_anak);

        //Get User Data From SharedPref
        final SharedPrefManager prefManager = new SharedPrefManager(this);
        UserModel user = prefManager.getUserLogin();
        String email = user.getUserMail(), username = user.getUserName(), password = user.getUserPassword(), image = user.getUserImage();
        tv_UserName.setText(username);
        Picasso.get()
                .load("http://192.168.43.247:3000/users/userImage/"+email)
                .fit()
                .centerCrop()
                .into(foto_profil_anak);

        int pembelajaranHariIni=0;

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        int progressLiniearA = mPrefs.getInt("progressA", 0);
        kelasA.setProgress(progressLiniearA);
        pembelajaranHariIni +=progressLiniearA;

       SharedPreferences mPrefs2 = getSharedPreferences("IDvalue2",0);
       int progressLiniearB = mPrefs2.getInt("progressB", 0);
       kelasB.setProgress(progressLiniearB);
       pembelajaranHariIni +=progressLiniearB;

        SharedPreferences mPrefs3 = getSharedPreferences("IDvalue3",0);
        int progressLiniearC = mPrefs3.getInt("progressC", 0);
        kelasC.setProgress(progressLiniearC);
        pembelajaranHariIni +=progressLiniearC;

        pencapaianHariIni.setProgress(pembelajaranHariIni);

        if (progressLiniearA == 100){
            txtPembelajaran1.setText("4 / 4 soal dikerjakan");
        }

        if (progressLiniearB == 100){
            txtPembelajaran2.setText("4 / 4 soal dikerjakan");
        }

        if (progressLiniearC == 100){
            txtPembelajaran3.setText("4 / 4 soal dikerjakan");
        }

        if (pembelajaranHariIni == 100){
            jumlahPencapaian.setText("4 / 12 pencapaian");
        }else if (pembelajaranHariIni == 200){
            jumlahPencapaian.setText("8 / 12 pencapaian");
        }else if (pembelajaranHariIni == 300){
            jumlahPencapaian.setText("12 / 12 pencapaian");
        }


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