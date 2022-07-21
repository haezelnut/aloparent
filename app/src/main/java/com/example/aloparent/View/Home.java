package com.example.aloparent.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aloparent.Artikel.Artikel2;
import com.example.aloparent.Artikel.Artikel4;
import com.example.aloparent.R;
import com.example.aloparent.SharedRefrence.SharedPrefManager;
import com.example.aloparent.SharedRefrence.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    public static LinearLayout inputDataAnak;
    public static LinearLayout layout1;
    public static LinearLayout layout2;
    public static RelativeLayout dataAnak;
    private CircleImageView foto_profil_orangtua;
    private TextView tv_UserName;

    SeekBar homeSeekbar;

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



    //inetnt ke profil orang tua
    public void ToProfile(View v){
        Intent intent = new Intent(Home.this, ProfileScreen.class);
        startActivity(intent);
    }

    //inten ke halaman notikasi
    public void toNotifikasi(View v){
        Intent intent = new Intent(Home.this, Notifikasi.class);
        startActivity(intent);
    }

    //imtemt ke halaman artikel
    public void allArticel(View v){
        Intent intent = new Intent(Home.this, Artikel.class);
        startActivity(intent);
    }

    //imtemt ke halaman artikel 4
    public void artikel4(View v){
        Intent intent = new Intent(Home.this, Artikel4.class);
        startActivity(intent);
    }

    //imtemt ke halaman artikel 2
    public void artikelHome2(View v){
        Intent intent = new Intent(Home.this, Artikel2.class);
        startActivity(intent);
    }

    //ke halaman semua data anak
    public void anotherChildData(View v){
        Intent intent = new Intent(Home.this, DataAnak.class);
        startActivity(intent);
    }

    //intent ke halaman favorit kesehatan
    public void kesehatanFavorit(View v){
        Intent intent = new Intent(Home.this, favorite.class);
        startActivity(intent);
    }

    // intent ke halaman semua permaianan
    public void allGames(View v){
        Intent intent = new Intent(Home.this, Permainan.class);
        startActivity(intent);
    }

    //intent ke halaman video hari ini
    public void allVideoToDay(View v){
        Intent intent = new Intent(Home.this, VideoHariIni.class);
        startActivity(intent);
    }

    // intent ke halaman input data anak
    public void inputDataAnak(View v){
        Intent intent = new Intent(Home.this, UpdateDataAnak.class);
        startActivity(intent);
    }

    public void profile(View v){
        Intent intent = new Intent(Home.this, ProfileScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.beranda);

        inputDataAnak = (LinearLayout) findViewById(R.id.tambahDataAnak);
        layout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        dataAnak = (RelativeLayout) findViewById(R.id.data_anak);
        layout2 = (LinearLayout) findViewById(R.id.linierlayout2);
        foto_profil_orangtua = findViewById(R.id.foto_profil_orangtua);
        tv_UserName = findViewById(R.id.tv_UserName);

        //Get User Data From SharedPref
        final SharedPrefManager prefManager = new SharedPrefManager(this);
        UserModel user = prefManager.getUserLogin();
        String email = user.getUserMail(), username = user.getUserName(), password = user.getUserPassword(), image = user.getUserImage();
        tv_UserName.setText(username);
        Picasso.get()
                .load("http://192.168.43.247:3000/users/userImage/"+email)
                .fit()
                .centerCrop()
                .into(foto_profil_orangtua);

        ImageSlider imageSlider = findViewById(R.id.view_pager1);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agar di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video2,"Agar di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Agar di Usian 3 Tahun Cerdas, yuk Biasakan ini dirumah"));

        imageSlider.setImageList(slideModels,true);

        homeSeekbar = findViewById(R.id.homeSeekbar);
        homeSeekbar.setEnabled(false);

        int totalSeekbar = 0;

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        int progressLiniearA = mPrefs.getInt("progressA", 0);

        totalSeekbar += progressLiniearA;

        SharedPreferences mPrefs2 = getSharedPreferences("IDvalue2",0);
        int progressLiniearB = mPrefs2.getInt("progressB", 0);

        totalSeekbar += progressLiniearB;

        SharedPreferences mPrefs3 = getSharedPreferences("IDvalue3",0);
        int progressLiniearC = mPrefs3.getInt("progressC", 0);

        totalSeekbar += progressLiniearC;

        homeSeekbar.setProgress(totalSeekbar);

        SharedPreferences simpanDataAnak = getSharedPreferences("simpanDataAnak",0);
       int data_Anak = simpanDataAnak.getInt("DATA_ANAK",0 );

       if (data_Anak == 1){
            inputDataAnak.setVisibility(View.GONE);
            dataAnak.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        }



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.beranda:
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
                        startActivity(new Intent(getApplicationContext(), Edukasi.class));
                        overridePendingTransition(0,0);
                    return true;

                }
                return false;
            }
        });

       if (!prefManager.IsUserLoggedIn()){
           backToLogin();
       }


    }
    private void backToLogin(){
        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        finish();
    }
}