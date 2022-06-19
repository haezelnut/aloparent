package com.example.aloparent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateKataSandi extends AppCompatActivity {

    private EditText inputKonfirmasiPasswordDaftar, inputKonfirmasiPasswordBaru;
    private ProgressBar progress;
    private AppCompatButton btnSendData;
    private ImageButton backFormProfile;
    private String email;

    public void backFormProfile(View v){
        Intent intent = new Intent(UpdateKataSandi.this, LupaPassword.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_kata_sandi);
        inputKonfirmasiPasswordDaftar = findViewById(R.id.inputKonfirmasiPasswordDaftar);
        inputKonfirmasiPasswordBaru = findViewById(R.id.inputKonfirmasiPasswordBaru);
        progress = findViewById(R.id.progress);
        btnSendData = findViewById(R.id.btnSendData);
        backFormProfile = findViewById(R.id.backFormProfile);
        Intent i = getIntent();
        email = i.getStringExtra("email");

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempPassword = inputKonfirmasiPasswordDaftar.getText().toString(), tempConfirmPassowrd = inputKonfirmasiPasswordBaru.getText().toString();
                if(tempPassword.isEmpty()){
                    inputKonfirmasiPasswordDaftar.setError("Kolom Kata Sandi Kosong !!!");
                    inputKonfirmasiPasswordDaftar.requestFocus();
                }else if(tempConfirmPassowrd.isEmpty()) {
                    inputKonfirmasiPasswordBaru.setError("Kolom Konfirmasi Kata Sandi Kosong !!!");
                    inputKonfirmasiPasswordBaru.requestFocus();
                }else if(tempPassword.equals(tempConfirmPassowrd)){
                    updatePassword(email, tempPassword);
                }else{
                    inputKonfirmasiPasswordBaru.setError("Konfirmasi Kata Sandi Tidak Sesuai !!!");
                    inputKonfirmasiPasswordBaru.requestFocus();
                }
            }
        });
    }

    private void updatePassword(String Uemail, String Upassword){/*
        progress.setVisibility(View.VISIBLE);*/
        JSONObject jsonObject = new JSONObject();
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://192.168.43.247:3000/users/reset";
            jsonObject.put("email", Uemail);
            jsonObject.put("password", Upassword);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        String res = response.getString("message");
                        System.out.println(res);
                        if (res.equals("TRUE")) {/*
                            progress.setVisibility(View.GONE);*/
                            Log.e("Response : ", response.toString());
                            Intent intent = new Intent(UpdateKataSandi.this, LupaPasswordNext.class);
                            startActivity(intent);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {/*
                    progress.setVisibility(View.GONE);*/
                    Toast.makeText(UpdateKataSandi.this, "Something Wrong !!!", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
