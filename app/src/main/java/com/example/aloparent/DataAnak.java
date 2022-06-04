package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aloparent.databinding.ActivityDataAnakBinding;

public class DataAnak extends AppCompatActivity {

    //intent ke update data anak
    public void toUpdateDataAnak(View v){
        Intent intent = new Intent(DataAnak.this, UpdateDataAnak.class);
        startActivity(intent);
    }

    // dari daftar data anak kembali ke screen home
    public void backFromDataAnak(View v){
        Intent intent = new Intent(DataAnak.this, Home.class);
        startActivity(intent);
    }

    ActivityDataAnakBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAnakBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}