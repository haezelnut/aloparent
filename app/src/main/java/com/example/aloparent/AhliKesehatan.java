package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AhliKesehatan extends AppCompatActivity {


    public void backToKesehatan(View v){
        Intent intent = new Intent(AhliKesehatan.this, KonselingKesehatanActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahli_kesehatan);

        Button btnPilihJadwal;
        Dialog dialog;

        btnPilihJadwal = findViewById(R.id.btnPiihJadwal);
        dialog = new Dialog(this);

        btnPilihJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pilihJadwal();
            }
            private void pilihJadwal(){
                dialog.setContentView(R.layout.pilih_jadwal_konsul);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                Button lanjut = dialog.findViewById(R.id.btn_lanjut);

                lanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AhliKesehatan.this, KonfirmasiJadwal.class);
                        finish();
                    }
                });

                dialog.show();
            }
        });
    }
}