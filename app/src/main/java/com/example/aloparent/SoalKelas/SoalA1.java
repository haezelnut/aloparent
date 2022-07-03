package com.example.aloparent.SoalKelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.example.aloparent.R;
import com.example.aloparent.SoalKelas.Message;

public class SoalA1 extends AppCompatActivity {

    RadioGroup radiogroupA2_1,radiogroupA2_2, radioGroupSoalA1;
    ProgressBar progressBar;
    LinearLayout soalA1, soalA2, soalA3, SoalA4;
    Button btn_cek;


    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radiogroupA2_2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                radiogroupA2_2.clearCheck(); // clear the second RadioGroup!
                radiogroupA2_2.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radiogroupA2_1.setOnCheckedChangeListener(null);
                radiogroupA2_1.clearCheck();
                radiogroupA2_1.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_a1);

        progressBar = (ProgressBar) findViewById(R.id.progressBarKelas);

        radioGroupSoalA1 = (RadioGroup) findViewById(R.id.radioGroupSoalA1);
        radiogroupA2_1 = (RadioGroup) findViewById(R.id.radioGroupSoalA2_1);
        radiogroupA2_2 = (RadioGroup) findViewById(R.id.radioGroupSoalA2_2);

        radiogroupA2_1.clearCheck();
        radiogroupA2_2.clearCheck();


        radiogroupA2_1.setOnCheckedChangeListener(listener1);

        soalA1 = (LinearLayout) findViewById(R.id.soala1);
        soalA2 = (LinearLayout) findViewById(R.id.soala2);

        btn_cek = (Button) findViewById(R.id.btn_cek_soalA);

        int progressKelasA = 0;

        switch (progressKelasA){
            case 0:
                soalA1.setVisibility(View.VISIBLE);
                soalA2.setVisibility(View.GONE);
                submitSoal1();

        }

   }

    private void submitSoal1() {
        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = radioGroupSoalA1.getCheckedRadioButtonId();
                if (checkedId == -1){
                    Message.Message(getApplicationContext(),"Silahkan pilih jawaban yang tepat");
                }else{
                    findOption(checkedId);
                }

            }
        });
    }

    private void findOption(int checkedId) {
        switch (checkedId){
            case R.id.a1_opsi1:
                Message.Message(getApplicationContext(),"");
        }
    }
}