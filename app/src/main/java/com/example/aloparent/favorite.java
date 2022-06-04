package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {

    public void backFromFavoritKesehatan(View v){
        Intent intent = new Intent(favorite.this, Home.class);
        startActivity(intent);
    }

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