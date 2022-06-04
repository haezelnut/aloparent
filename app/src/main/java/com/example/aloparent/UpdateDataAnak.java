package com.example.aloparent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateDataAnak extends AppCompatActivity {

    Button btnSimpan;
    Dialog dialog;

    private DatePickerDialog datePickerDialog;
    private AppCompatButton btn_TakePhoto;
    private Button btn_DatePicker;
    private CircleImageView img_Profile;

    private AppCompatButton btn_simpan;

    public void backFromUpdateDataAnak(View v){
            onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_anak);
        initDatePicker();
        btn_DatePicker = findViewById(R.id.btn_DatePicker);
        btn_TakePhoto = findViewById(R.id.btn_TakePhoto);
        img_Profile = findViewById(R.id.img_Profile);
        btn_TakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdateDataAnak.this)
                        .cropSquare()
                        .maxResultSize(1080,1080)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


        btn_DatePicker.setText(getTodayDate());

        //pop up dialogbox
        btnSimpan = findViewById(R.id.btnSimpan);
        dialog = new Dialog(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // merubah tampilan home 1 ke home 2 setelah data anak diimput

                Home.inputDataAnak.setVisibility(View.GONE);
                Home.dataAnak.setVisibility(View.VISIBLE);
                Home.layout1.setVisibility(View.GONE);
                Home.layout2.setVisibility(View.VISIBLE);

                openBerhasilSimpan();
            }
            private void openBerhasilSimpan() {
                dialog.setContentView(R.layout.data_berhasil_disimpan);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button lihatData = dialog.findViewById(R.id.lihatData);


                lihatData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(UpdateDataAnak.this, Home.class);
                        finish();
                        Toast.makeText(UpdateDataAnak.this, "Data telah disimpan", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        //visible invisble home 1 and 2

        /* btn_simpan =(AppCompatButton) findViewById(R.id.btnSimpan);

        Home.layout2.setVisibility(View.GONE);


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UpdateDataAnak.this, Home.class);

                Home.inputDataAnak.setVisibility(View.GONE);
                Home.dataAnak.setVisibility(View.VISIBLE);
                Home.layout1.setVisibility(View.GONE);
                Home.layout2.setVisibility(View.VISIBLE);

                finish();

            }
        }); */
    }

    //Date Code
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

    //Image Photo Code

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        img_Profile.setImageURI(uri);
    }
}