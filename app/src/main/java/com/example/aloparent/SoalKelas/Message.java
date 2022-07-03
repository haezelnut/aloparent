package com.example.aloparent.SoalKelas;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void Message(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
