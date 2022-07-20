package com.example.aloparent.SoalKelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aloparent.R;
import com.example.aloparent.View.Kelas;

import java.util.ArrayList;
import java.util.List;

public class SoalB extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(SoalB.this, Kelas.class);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Sekali lagi Untuk Kembali ke Menu kelas", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    RadioGroup radioGroupSoalA1;
    ProgressBar progressBar;
    Button btn_cek;
    Dialog dialog;
    AppCompatRadioButton jawaban1, jawaban2, jawaban3, jawaban4;
    TextView pertanyaan, pertanyaan2, txtJawaban1, txtJawaban2,txtJawaban3,txtJawaban4;
    ImageView imgPertanyaan, imgJawaban1, imgJawaban2, imgJawaban3, imgJawaban4;
    LinearLayout linearLayout;

    SoalModel currentQuestion;
    int indexCurrentQuestion = 0;
    int totalQuestion;
    boolean answered;
    int progresKelasA = 25;
    List<SoalModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_b);

        progressBar = (ProgressBar) findViewById(R.id.progressBarKelas);

        radioGroupSoalA1 = (RadioGroup) findViewById(R.id.radioGroupSoalA1);

        jawaban1 = findViewById(R.id.a1_opsi1);
        jawaban2 = findViewById(R.id.a1_opsi2);
        jawaban3 = findViewById(R.id.a1_opsi3);
        jawaban4 = findViewById(R.id.a1_opsi4);

        imgPertanyaan = findViewById(R.id.imgViewPertanyaan);

        pertanyaan = findViewById(R.id.textViewPertanyaan);
        pertanyaan2 = findViewById(R.id.textViewPertanyaan2);
        txtJawaban1 = findViewById(R.id.jawaban1);
        txtJawaban2 =  findViewById(R.id.jawaban2);
        txtJawaban3 =  findViewById(R.id.jawaban3);
        txtJawaban4 =  findViewById(R.id.jawaban4);

        btn_cek = (Button) findViewById(R.id.btn_cek_soalA);
        dialog = new Dialog(this);

        questionsList = new ArrayList<>();

        addQuestions();

        totalQuestion = questionsList.size();

        showNextQuestion();

        progressBar.setProgress(progresKelasA);

        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answered = true;
                RadioButton radioSelected = findViewById(radioGroupSoalA1.getCheckedRadioButtonId());
                int answerNo = radioGroupSoalA1.indexOfChild(radioSelected) + 1;

                Log.e("ANSWER_NO", String.valueOf(answerNo));
                if(answerNo == currentQuestion.getKunciJawaban()) {
                    correctAnswerDialog();
                } else {
                    Message.Message(getApplicationContext(),"Jawabanya kurang tepat nih, perhatikan soalnya kembali yaa");
                }
            }
        });
    }

    private void showNextQuestion() {
        if(indexCurrentQuestion < totalQuestion) {
            adjustQuestion();

            currentQuestion = questionsList.get(indexCurrentQuestion);

            if(currentQuestion.getPertanyaan().length() > 0) {
                pertanyaan.setText(currentQuestion.getPertanyaan());
                pertanyaan.setVisibility(View.VISIBLE);
                // txtQuestion.setHeight(txtQuestion.getLayoutParams().WRAP_CONTENT);
            } else {
                pertanyaan.setVisibility(View.GONE);
                // txtQuestion.setHeight(0);
            }

            if(currentQuestion.getPertanyaan2().length() > 0) {
                pertanyaan2.setText(currentQuestion.getPertanyaan2());
                pertanyaan2.setVisibility(View.VISIBLE);
                // txtQuestion.setHeight(txtQuestion.getLayoutParams().WRAP_CONTENT);
            } else {
                pertanyaan2.setVisibility(View.GONE);
                // txtQuestion.setHeight(0);
            }

            if(currentQuestion.getImgPertanyaan() > 0) {
                imgPertanyaan.setImageResource(currentQuestion.getImgPertanyaan());
            } else {
                imgPertanyaan.setImageDrawable(null);
            }

            if(currentQuestion.getImgJawaban1() > 0) {
                txtJawaban1.setBackgroundResource(currentQuestion.getImgJawaban1());
            } else {
                txtJawaban1.setText(currentQuestion.getJawaban1());
            }

            if(currentQuestion.getImgJawaban2() > 0) {
                txtJawaban2.setBackgroundResource(currentQuestion.getImgJawaban2());
            } else {
                txtJawaban2.setText(currentQuestion.getJawaban2());
            }

            if(currentQuestion.getImgJawaban3() > 0) {
                txtJawaban3.setBackgroundResource(currentQuestion.getImgJawaban3());
            } else {
                txtJawaban3.setText(currentQuestion.getJawaban3());
            }

            if(currentQuestion.getImgJawaban4() > 0) {
                txtJawaban4.setBackgroundResource(currentQuestion.getImgJawaban4());
            } else {
                txtJawaban4.setText(currentQuestion.getJawaban4());
            }


            indexCurrentQuestion++;
            btn_cek.setText("Submit");;
            answered = false;

        }
    }

    private void adjustQuestion() {
        if (indexCurrentQuestion == 0){

            int height = 850;
            int width = 325;

            radioGroupSoalA1.getLayoutParams().width = 90;

            // linearLayout.getLayoutParams().width = width;

            txtJawaban1.setHeight(txtJawaban1.getLayoutParams().height = height);
            txtJawaban2.setHeight(txtJawaban2.getLayoutParams().height = height);
            txtJawaban3.setHeight(txtJawaban3.getLayoutParams().height = height);
            txtJawaban4.setHeight(txtJawaban4.getLayoutParams().height = height);

            txtJawaban1.setWidth(txtJawaban1.getLayoutParams().width = width);
            txtJawaban2.setWidth(txtJawaban2.getLayoutParams().width = width);
            txtJawaban3.setWidth(txtJawaban3.getLayoutParams().width = width);
            txtJawaban4.setWidth(txtJawaban4.getLayoutParams().width = width);

            jawaban1.setHeight(jawaban1.getLayoutParams().height = height);
            jawaban2.setHeight(jawaban2.getLayoutParams().height = height);
            jawaban3.setHeight(jawaban3.getLayoutParams().height = height);
            jawaban4.setHeight(jawaban4.getLayoutParams().height = height);
        }

         if (indexCurrentQuestion == 1){
            int height = 150;
            int width = 400;


            txtJawaban1.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban2.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban3.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban4.setBackgroundResource(R.drawable.rectangle_edittext);

            txtJawaban1.setElevation(10);
            txtJawaban2.setElevation(10);
            txtJawaban3.setElevation(10);
            txtJawaban4.setElevation(10);

            txtJawaban1.setWidth(txtJawaban1.getLayoutParams().width = width);
            txtJawaban2.setWidth(txtJawaban2.getLayoutParams().width = width);
            txtJawaban3.setWidth(txtJawaban3.getLayoutParams().width = width);
            txtJawaban4.setWidth(txtJawaban4.getLayoutParams().width = width);


            txtJawaban1.setHeight(txtJawaban1.getLayoutParams().height = height);
            txtJawaban2.setHeight(txtJawaban2.getLayoutParams().height = height);
            txtJawaban3.setHeight(txtJawaban3.getLayoutParams().height = 200);
            txtJawaban4.setHeight(txtJawaban4.getLayoutParams().height = height);

            jawaban1.setHeight(jawaban1.getLayoutParams().height = height);
            jawaban2.setHeight(jawaban2.getLayoutParams().height = height);
            jawaban3.setHeight(jawaban3.getLayoutParams().height = 210);
            jawaban4.setHeight(jawaban4.getLayoutParams().height = height);
        }
        if (indexCurrentQuestion == 2){

            int height = 400;
            int width = 400;

            txtJawaban1.setHeight(txtJawaban1.getLayoutParams().height = height);
            txtJawaban2.setHeight(txtJawaban2.getLayoutParams().height = height);
            txtJawaban3.setHeight(txtJawaban3.getLayoutParams().height = height);
            txtJawaban4.setHeight(txtJawaban4.getLayoutParams().height = height);

            txtJawaban1.setWidth(txtJawaban1.getLayoutParams().width = width);
            txtJawaban2.setWidth(txtJawaban2.getLayoutParams().width = width);
            txtJawaban3.setWidth(txtJawaban3.getLayoutParams().width = width);
            txtJawaban4.setWidth(txtJawaban4.getLayoutParams().width = width);

            jawaban1.setHeight(jawaban1.getLayoutParams().height = height);
            jawaban2.setHeight(jawaban2.getLayoutParams().height = height);
            jawaban3.setHeight(jawaban3.getLayoutParams().height = height);
            jawaban4.setHeight(jawaban4.getLayoutParams().height = height);

            txtJawaban1.setText("");
            txtJawaban2.setText("");
            txtJawaban3.setText("");
            txtJawaban4.setText("");

        }

        if (indexCurrentQuestion == 3){

            int height = 780;
            int width = 400;

            txtJawaban1.setHeight(txtJawaban1.getLayoutParams().height = height);
            txtJawaban2.setHeight(txtJawaban2.getLayoutParams().height = height);
            txtJawaban3.setHeight(txtJawaban3.getLayoutParams().height = height);
            txtJawaban4.setHeight(txtJawaban4.getLayoutParams().height = height);

            txtJawaban1.setWidth(txtJawaban1.getLayoutParams().width = width);
            txtJawaban2.setWidth(txtJawaban2.getLayoutParams().width = width);
            txtJawaban3.setWidth(txtJawaban3.getLayoutParams().width = width);
            txtJawaban4.setWidth(txtJawaban4.getLayoutParams().width = width);

            jawaban1.setHeight(jawaban1.getLayoutParams().height = height);
            jawaban2.setHeight(jawaban2.getLayoutParams().height = height);
            jawaban3.setHeight(jawaban3.getLayoutParams().height = height);
            jawaban4.setHeight(jawaban4.getLayoutParams().height = height);


        }


    }

    private void addQuestions() {
        questionsList.add(new SoalModel(
                "Suatu sore Didi ingin menyusun balok-balok dibawah ini", R.drawable.img_soalb1,
                "Ia ingin menyusun jika ada warna yang sama maka akan disusun berdampingan, Ia juga tidak akan menyusun balok yang mempunyai warna tidak sama dengan yang lain. \n" +
                        "\n" +
                        "Dapatkah kamu membantunya?",
                "", R.drawable.img_soalb1_opsi1,
                "", R.drawable.img_soalb1_opsi2,
                "",R.drawable.img_soalb1_opsi3,
                "", R.drawable.img_soalb1_opsi4,
                4));
        questionsList.add(new SoalModel(
                "Seekor landak ingin pergi ke rumah semut. Ia ingin mengumpulkan semua semut merah. Dapatkah kamu menemukan arah jalan agar landak bisa mengumpulkan semua semut sebelum sampai ke rumah semut?",R.drawable.img_soalb2,
                "",
                "Kanan, Naik, Kanan Turun, Kanan Naik, Kanan",0,
                "Kanan, Naik, Kanan, Naik, Kiri, Turun, Kanan",0,
                "Kanan, Naik, Kanan, Naik, Kanan, Naik, Kanan, Turun, Kanan, Turun, Kanan",0,
                "Kanan, Naik, Kanan, Turun, Kanan, Turun, Kanan",0,
                1));
        questionsList.add(new SoalModel(
                "Didi sedang bermain mobil-mobilan, namun tidak sengaja menabrak kotak berisi mainannya sehingga berantakan seperti berikut",R.drawable.img_soalb3,
                "Ia ingin memisahkan mainannya. Mainan berbentuk lingkaran akan dimasukkan ke kotak jingga, bentuk persegi dimasukkan ke kotak merah muda, dan bentuk segitiga dimasukkan ke kotak hijau.\n" +
                        "\n" +
                        "Dapatkah kamu membantu Didi mengelompokkan sesuai bentuk, warna, dan jumlahnya?",
                "",R.drawable.img_soalb3_opsi1,
                "",R.drawable.img_soalb3_opsi2,
                "",R.drawable.img_soalb3_opsi3,
                "",R.drawable.img_soalb3_opsi4,
                2
        ));
        questionsList.add(new SoalModel(
                "Roy sedang menata pakaian dengan Bunda. Ia ingin pakaian disusun berututan mulai yang dipakai dari atas sampai bawah. Manakah urutan yang tepat?",R.drawable.img_soalb4,
                "",
                "",R.drawable.img_soalb4_opsi1,
                "",R.drawable.img_soalb4_opsi2,
                "",R.drawable.img_soalb4_opsi3,
                "",R.drawable.img_soalb4_opsi4,
                2));
    }

    private void correctAnswerDialog() {
        dialog.setContentView(R.layout.jawaban_benar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button lihatSoal = dialog.findViewById(R.id.btn_lihatSoal);
        Button lanjut = dialog.findViewById(R.id.btn_lanjut);

        dialog.show();

        lihatSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexCurrentQuestion < totalQuestion) {
                    progresKelasA +=25;
                    progressBar.setProgress(progresKelasA);
                    showNextQuestion();
                    dialog.cancel();
                } else {
                    dialog.setContentView(R.layout.soal_selesai);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    Button Btn_oke = dialog.findViewById(R.id.btn_oke);

                    Btn_oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int progressB = 100;
                            SharedPreferences mPrefs = getSharedPreferences("IDvalue2", 0);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putInt("progressB", progressB);
                            editor.commit();
                            Intent intent = new Intent(SoalB.this, Kelas.class);
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }
}