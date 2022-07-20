package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.aloparent.R;

import java.util.ArrayList;
import java.util.List;

public class favorite extends AppCompatActivity {

    public void backFromFavoritKesehatan(View v){
        Intent intent = new Intent(favorite.this, Home.class);
        startActivity(intent);
    }
    public void toArtikel(View v){
        Intent intent = new Intent(favorite.this, Artikel.class);
        startActivity(intent);
    }
    public void toVideo(View v){
        Intent intent = new Intent(favorite.this, VideoHariIni.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        // image slider video kesehatan

        ImageSlider imageSlider = findViewById(R.id.view_pager_video_kesehatan);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video2,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));
        slideModels.add(new SlideModel(R.drawable.contoh_gambar_video1,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));

        imageSlider.setImageList(slideModels,true);

        // image slider artikel kesehatan

        ImageSlider imageSlider2 = findViewById(R.id.view_pager_artikel_kesehatan);

        List<SlideModel> slideModels2 = new ArrayList<>();

        slideModels2.add(new SlideModel(R.drawable.contoh_gambar_video1,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));
        slideModels2.add(new SlideModel(R.drawable.contoh_gambar_video2,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));
        slideModels2.add(new SlideModel(R.drawable.contoh_gambar_video1,"Praktek langsung mengenal kesehatan pada anak 3 Tahun"));

        imageSlider2.setImageList(slideModels,true);


    }
}