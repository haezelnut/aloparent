package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> images = new ArrayList<>();
    ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        viewPager = findViewById(R.id.view_pager1);

        images.add(R.drawable.contoh_gambar_video1);
        images.add(R.drawable.contoh_gambar_video2);

        adapter = new ViewPagerAdapter(this,images);
        viewPager.setPadding(100,0,100,0);



        viewPager.setAdapter(adapter);


    }
}