package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LupaPassword extends AppCompatActivity {

    private EditText inputUserName, inputEmailLupaPw;
    private String tempUserName, tempEmail,  tempEmail2;
    private ProgressBar progress;


    public void backFromLupaPw(View v){
        Intent intent = new Intent(LupaPassword.this, LoginScreen.class);
        startActivity(intent);
    }
    public void btnKirimLupaPw(View v){
        tempUserName = inputUserName.getText().toString();
        tempEmail = inputEmailLupaPw.getText().toString();
        tempEmail2 = tempEmail;

        if(tempUserName.isEmpty()){
            inputUserName.setError("Kolom Orang Tua Kosong !!!");
            inputUserName.requestFocus();
        }else if(tempEmail.isEmpty()) {
            inputEmailLupaPw.setError("Kolom Email Kosong !!!");
            inputEmailLupaPw.requestFocus();
        }else {
            checkAccount(tempUserName, tempEmail);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        inputUserName = findViewById(R.id.inputUserName);
        inputEmailLupaPw = findViewById(R.id.inputEmailLupaPw);
        progress = findViewById(R.id.progress);
    }

    private void checkAccount (String username, String email){
        progress.setVisibility(View.VISIBLE);
        JSONObject object = new JSONObject();
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://192.168.43.247:3000/users/check";
            object.put("username", username);
            object.put("email", email);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        String res = response.getString("message");
                        System.out.println(res);
                        if (res.equals("TRUE")) {
                            progress.setVisibility(View.GONE);
                            Log.e("Response : ", response.toString());
                            Intent intent = new Intent(LupaPassword.this, UpdateKataSandi.class);
                            intent.putExtra("email", tempEmail2);
                            startActivity(intent);
                        }
                    }catch (JSONException e){
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
                        System.out.println(errorLog);
                        if (errorLog.equals("EMAIL FALSE")) {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(LupaPassword.this, "E-Mail Tidak Terdaftar !!!", Toast.LENGTH_SHORT).show();
                        }else if(errorLog.equals("USERNAME FALSE")){
                            progress.setVisibility(View.GONE);
                            Toast.makeText(LupaPassword.this, "Username Tidak Terdaftar !!!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}