package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aloparent.databinding.ActivityDataAnakBinding;

public class DataAnak extends AppCompatActivity {

    ActivityDataAnakBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAnakBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}