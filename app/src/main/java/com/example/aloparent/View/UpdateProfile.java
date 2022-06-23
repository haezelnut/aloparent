package com.example.aloparent.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.example.aloparent.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {

    Dialog dialog;
    Bitmap bitmap;
    String encodeImageString;

    private CircleImageView profile_picture;

    private AppCompatButton btn_TakePhoto, btnSimpan;
    private EditText inputUsername, inputEmailLogin, inputKataSandi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btnSimpan = findViewById(R.id.btnSimpanData);
        btn_TakePhoto = findViewById(R.id.btn_TakePhoto);
        inputUsername = findViewById(R.id.inputUsername);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputKataSandi = findViewById(R.id.inputKataSandi);
        profile_picture = findViewById(R.id.profile_picture);

        btn_TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdateProfile.this)
                        .cropSquare()
                        .maxResultSize(1080,1080)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }



    private void encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byteImg = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(byteImg, Base64.DEFAULT);
        System.out.println(encodeImageString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        try{
            InputStream inputStream = getContentResolver().openInputStream(uri);
            bitmap  = BitmapFactory.decodeStream(inputStream);
            profile_picture.setImageBitmap(bitmap);
            encodeBitmapImage(bitmap);
        }catch (Exception e){

        }
    }

    public void backFormUbahData(View v){
        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }

    public void btnSimpanData(View v){
        Intent intent = new Intent(UpdateProfile.this, ProfileScreen.class);
        startActivity(intent);
    }
}