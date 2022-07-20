package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.R;
import com.example.aloparent.SoalKelas.SoalA;
import com.example.aloparent.SoalKelas.SoalB;

public class PanduanOrangTuaScreen extends AppCompatActivity {
    AppCompatButton lanjut;

    public void backFromPanduanOrtu(View v){
        Intent intent = new Intent(PanduanOrangTuaScreen.this, Kelas.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_orang_tua_screen);

        lanjut =  findViewById(R.id.button);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanduanOrangTuaScreen.this, SoalA.class);
                startActivity(intent);
            }
        });
    }
}