package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoHariIni extends AppCompatActivity {

    public void backFromVideoHariIni(View v){
        Intent intent = new Intent(VideoHariIni.this, Home.class);
        startActivity(intent);
    }

    YouTubePlayerView youtube_player_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_hari_ini);

        youtube_player_view = findViewById(R.id.youtube_player_view);
    }
}