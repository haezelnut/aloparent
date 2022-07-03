package com.example.aloparent.SharedRefrence;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    // library shred refrence
    final SharedPreferences preferences;

    // deklarasi variavel
    private static final String SHARED_PREF_NAME = "MY_APP_PREF";
    private static final String USER_KEY = "USER_EMAIL";
    private static final String NAME_KEY = "USER_NAME";
    private static final String PASSWORD_KEY = "USER_PASSWORD";
    private static final String IMAGE_KEY = "USER_IMAGE";
    private static final String IS_LOGGER_IN_KEY = "ISLOGGEDIN";

    // methord hanya aplikasi ini yang dapat menggunakan shared refrence
    public SharedPrefManager(Context context){
        preferences= context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    // method membaca data sesuai key

    public UserModel getUserLogin(){
        return new UserModel(
                preferences.getString(USER_KEY, null),
                preferences.getString(NAME_KEY, null),
                preferences.getString(PASSWORD_KEY, null),
                preferences.getString(IMAGE_KEY, null)
        );
        }

    //method menyimpan status data login yang telah dibaca

    public void setUserLogin(UserModel user, Boolean IsloggedIn){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_KEY,user.getUserMail());
        editor.putString(NAME_KEY, user.getUserName());
        editor.putString(PASSWORD_KEY, user.getUserPassword());
        editor.putString(IMAGE_KEY, user.getUserImage());
        editor.putBoolean(IS_LOGGER_IN_KEY, IsloggedIn);
        editor.apply();
    }

    // method membaca apakah data sudah login atau belum, jika belum akan dikembalikan ke halaman login

    public Boolean IsUserLoggedIn(){
        return preferences.getBoolean(IS_LOGGER_IN_KEY, false);
    }

    // method untuk logout
    public void userLogout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

    }
    }

