package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoHariIni extends AppCompatActivity {

    public void backFromVideoHariIni(View v){
        Intent intent = new Intent(VideoHariIni.this, Home.class);
        startActivity(intent);
    }

    YouTubePlayerView video_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_hari_ini);
    }
}