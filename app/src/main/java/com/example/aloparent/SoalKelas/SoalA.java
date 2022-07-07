package com.example.aloparent.SoalKelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aloparent.R;
import com.example.aloparent.View.Kelas;

import java.util.ArrayList;
import java.util.List;

public class SoalA extends AppCompatActivity {

    RadioGroup radioGroupSoalA1;
    ProgressBar progressBar;
    Button btn_cek;
    Dialog dialog;
    AppCompatRadioButton jawaban1, jawaban2, jawaban3, jawaban4;
    TextView pertanyaan, txtJawaban1, txtJawaban2,txtJawaban3,txtJawaban4;
    ImageView imgPertanyaan;

    SoalModel currentQuestion;
    int indexCurrentQuestion = 0;
    int totalQuestion;
    boolean answered;
    int progresKelasA = 25;
    List<SoalModel> questionsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_a1);

        progressBar = (ProgressBar) findViewById(R.id.progressBarKelas);

        radioGroupSoalA1 = (RadioGroup) findViewById(R.id.radioGroupSoalA1);

        jawaban1 = findViewById(R.id.a1_opsi1);
        jawaban2 = findViewById(R.id.a1_opsi2);
        jawaban3 = findViewById(R.id.a1_opsi3);
        jawaban4 = findViewById(R.id.a1_opsi4);

        imgPertanyaan = findViewById(R.id.imgViewPertanyaan);

        pertanyaan = findViewById(R.id.textViewPertanyaan);
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

            currentQuestion = questionsList.get(indexCurrentQuestion);

            if(currentQuestion.getPertanyaan().length() > 0) {
                pertanyaan.setText(currentQuestion.getPertanyaan());
                pertanyaan.setVisibility(View.VISIBLE);
                // txtQuestion.setHeight(txtQuestion.getLayoutParams().WRAP_CONTENT);
            } else {
                pertanyaan.setVisibility(View.GONE);
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


    private void addQuestions() {
        questionsList.add(new SoalModel(
                "Pagi ini seperti biasa setelah bangun tidur, Andi akan mandi. Namun, Andi ingin bermain lompat-lompatan. \n" +
                        "\n" +
                        "Dibawah ini sudah ada kotak berisi barang barang, Andi dapat berhenti pada kotak yang berisikan peralatan mandi, dan harus melompat pada kotak yang tidak berisikan peralatan mandi. \n" +
                        "\n" +
                        "Berapa kotak Andi dapat berhenti (tidak melompat)?", R.drawable.gambar_soala1,
                "3 kali", 0,
                "2 kali", 0,
                "1 kali",0,
                "Melompat terus", 0,
                1));
        questionsList.add(new SoalModel(
                "Cari median dari data pembuatan roti di sebuah pabrik dalam beberapa hari berikut.\n 20, 30, 25, 32, 26, 33, 36, 34, 28, 29", 0,
                "A. 29", 0,
                "B. 29,5", 0,
                "C. 30", 0,
                "D. 28,5", 0,
                2));
        questionsList.add(new SoalModel(
                "Hasil panen kakek selama 5 bulan dalam ton adalah 10, 6, 7, 9, 8. Rata-rata hasil panen kakek tiap bulan adalah", 0,
                "A. 7", 0,
                "B. 8", 0,
                "C. 9", 0,
                "D. 10", 0,
                2));
        questionsList.add(new SoalModel(
                "Rata-rata dan median dari data di atas adalah", R.drawable.games_image,
                "A. Rata-rata = 7,5 dan median = 7", 0,
                "B. Rata-rata = 7,5 dan median = 7,5", 0,
                "C. Rata-rata = 7 dan median = 7,5", 0,
                "D. Rata-rata = 7,5 dan median = 8", 0,
                1));
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
                            Intent intent = new Intent(SoalA.this, Kelas.class);
                            startActivity(intent);
                        }
                    });
                    }

            }
        });
    }
}