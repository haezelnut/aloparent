package com.example.aloparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class UpdateDataAnak extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button btn_DatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_anak);
        initDatePicker();
        btn_DatePicker = findViewById(R.id.btn_DatePicker);
        btn_DatePicker.setText(getTodayDate());
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month - 12  ;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(day, month, year);
                btn_DatePicker.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year){
        return day + " " + getMonthFormat(month)  + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Januari";
        if(month == 2)
            return "Februari";
        if(month == 3)
            return "Maret";
        if(month == 4)
            return "April";
        if(month == 5)
            return "Mei";
        if(month == 6)
            return "Juni";
        if(month == 7)
            return "Juli";
        if(month == 8)
            return "Agustus";
        if(month == 9)
            return "September";
        if(month == 10)
            return "Oktober";
        if(month == 11)
            return "November";
        if(month == 12)
            return "Desember";

        return "Januari";
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }
}