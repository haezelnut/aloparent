package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static LinearLayout inputDataAnak;
    public static LinearLayout layout1;
    public static LinearLayout layout2;
    public static RelativeLayout dataAnak;

    ViewPager viewPager;
    ArrayList<Integer> images = new ArrayList<>();
    ViewPagerAdapter adapter;


    public void inputDataAnak(View v){
        Intent intent = new Intent(Home.this, UpdateDataAnak.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inputDataAnak = (LinearLayout) findViewById(R.id.tambahDataAnak);
        layout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        dataAnak = (RelativeLayout) findViewById(R.id.data_anak);
        layout2 = (LinearLayout) findViewById(R.id.linierlayout2);


        viewPager = findViewById(R.id.view_pager1);

        images.add(R.drawable.contoh_gambar_video1);
        images.add(R.drawable.contoh_gambar_video2);

        adapter = new ViewPagerAdapter(this,images);
        viewPager.setPadding(0,0,0,0);



        viewPager.setAdapter(adapter);
    }


}