package com.example.aloparent.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.aloparent.R;
import com.example.aloparent.SoalKelas.SoalA;
import com.example.aloparent.SoalKelas.SoalB;

public class PanduanOrangTuaScreen2 extends AppCompatActivity {

    AppCompatButton lanjut;
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jawaban_benar);

        /*lanjut = findViewById(R.id.button);
        kembali = findViewById(R.id.imageButton);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PanduanOrangTuaScreen2.this, SoalB.class);
                startActivity(intent);
            }
        }); */
    }
}
