package com.example.aloparent.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloparent.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RegisterScreen extends AppCompatActivity {

    EditText inputPassword, inputConfirm, inputNamaOrangTua, inputEmailLogin;
    boolean passwordVisible;

    public void toLogin(View v){
        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    public void btnDaftar(View v){
        String tempNamaOrangTua = inputNamaOrangTua.getText().toString(), tempEmail = inputEmailLogin.getText().toString(), tempPassword = inputPassword.getText().toString(), tempConfirm= inputConfirm.getText().toString();
        if(tempNamaOrangTua.isEmpty()){
            inputNamaOrangTua.setError("Kolom Orang Tua Kosong !!!");
            Toast.makeText(this, "Mohon Isi Semua Kolom !!!", Toast.LENGTH_SHORT).show();
        }else if(tempEmail.isEmpty()){
            inputEmailLogin.setError("Kolom Email Kosong !!!");
            Toast.makeText(this, "Mohon Isi Semua Kolom !!!", Toast.LENGTH_SHORT).show();
        }else if(tempPassword.isEmpty()){
            inputPassword.setError("Kolom Password Kosong !!!");
            Toast.makeText(this, "Mohon Isi Semua Kolom !!!", Toast.LENGTH_SHORT).show();
        }else if (tempConfirm.isEmpty()){
            inputConfirm.setError("Kolom Confirm Password Kosong !!!");
            Toast.makeText(this, "Mohon Isi Semua Kolom !!!", Toast.LENGTH_SHORT).show();
        }else{
            postData(randID(), inputNamaOrangTua.getText().toString(), inputEmailLogin.getText().toString(), inputPassword.getText().toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        inputNamaOrangTua = findViewById(R.id.inputNamaOrangTua);
        inputEmailLogin = findViewById(R.id.inputEmailLogin);
        inputPassword = findViewById(R.id.inputPasswordDaftar);
        inputConfirm = findViewById(R.id.inputKonfirmasiPasswordDaftar);

        // hide dan show icon on inputtext password
        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=inputPassword.getRight()-inputPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=inputPassword.getSelectionEnd();
                        if(passwordVisible){
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off , 0 );
                            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility , 0 );
                            inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        inputPassword.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        // hide dan show icon on inputtext konfirmasi
        inputConfirm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=inputConfirm.getRight()-inputConfirm.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=inputPassword.getSelectionEnd();
                        if(passwordVisible){
                            inputConfirm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off , 0 );
                            inputConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            inputConfirm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility , 0 );
                            inputConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        inputConfirm.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }

    private int randID() {
        Random rand = new Random();
        int upperBound = 1000000000;
        int int_random = rand.nextInt(upperBound);
        return int_random;
    }

    private void postData(Integer id_user, String temp_username, String temp_email, String temp_Password) {
        JSONObject object = new JSONObject();
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://192.168.43.247:3000/users";
            object.put("id_user", id_user);
            object.put("username", temp_username);
            object.put("email", temp_email);
            object.put("password", temp_Password);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String res = response.getString("message");
                        System.out.println(res);

                        if (res.equals("SUCCESS")) {
                            Log.e("Response : ", response.toString());
                            Toast.makeText(RegisterScreen.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                            inputNamaOrangTua.setText("");
                            inputEmailLogin.setText("");
                            inputPassword.setText("");
                            inputConfirm.setText("");
                            Intent intent = new Intent(RegisterScreen.this, BerhasilDaftar.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String responseBody = null;
                    responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    try {
                        JSONObject data = new JSONObject(responseBody);
                        String errorLog = data.getString("message");
                        if (errorLog.equals("EXIST")) {
                            Toast.makeText(RegisterScreen.this, "E-Mail Telah Terdaftar !!!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}