package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    private EditText inputEmail,inputPassword;
    private boolean passwordVisible;
    private ProgressBar progressBar;

    public void forgetPass(View v){
        Intent intent = new Intent(LoginScreen.this, LupaPassword.class);
        startActivity(intent);
    }

    public void toRegister(View v){
        Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
        startActivity(intent);
    }
    public void btnMasuk(View v){
        String temp_email = inputEmail.getText().toString(), temp_password = inputPassword.getText().toString();
        if(temp_email.isEmpty()){
            inputEmail.setError("Silahkan isi kolom e-mail...");
            inputEmail.requestFocus();
        }else if (temp_password.isEmpty()){
            inputPassword.setError("Silahkan isi kolom password...");
            inputPassword.requestFocus();
        }else{
            sendData(temp_email, temp_password);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        inputEmail = findViewById(R.id.inputEmailLogin);
        inputPassword = findViewById(R.id.inputPasswordLogin);
        progressBar = findViewById(R.id.progress);

        // show and hide password on password edittext
        inputPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=inputPassword.getRight()-inputPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=inputPassword.getSelectionEnd();
                        if(passwordVisible){
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility , 0 );
                            inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            inputPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off , 0 );
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
    }

    private void sendData(String temp_Email, String temp_password) {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.43.247:3000/users/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("",response);
                if(response.equals("TRUE")){
                    //Starting Home activity
                    Intent intent = new Intent(LoginScreen.this, BerhasilLogin.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginScreen.this, "Login Successful !!!", Toast.LENGTH_SHORT).show();
                }else if(response.equals("PASSWORD FALSE")){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginScreen.this, "Password is Wrong !!!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("", String.valueOf(error));
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginScreen.this, "E-Mail Not Found !!!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email",temp_Email.trim());
                params.put("password",temp_password.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}