package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {

    EditText inputPassword, inputConfirm;
    boolean passwordVisible;

    public void toLogin(View v){
        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    public void btnDaftar(View v){
        Intent intent = new Intent(RegisterScreen.this, BerhasilDaftar.class);
        startActivity(intent);
    }

    public void backToRegister(View v){
        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

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
}