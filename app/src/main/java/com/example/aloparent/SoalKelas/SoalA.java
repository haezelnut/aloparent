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
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.aloparent.View.PanduanOrangTuaScreen;

import java.util.ArrayList;
import java.util.List;

public class SoalA extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(SoalA.this, Kelas.class);
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
        setContentView(R.layout.activity_soal_a1);

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

            adjustQuestion();


            indexCurrentQuestion++;
            btn_cek.setText("Submit");;
            answered = false;

        }
    }

    private void adjustQuestion() {
        if(indexCurrentQuestion == 1){
            txtJawaban1.setText("");
            txtJawaban2.setText("");
            txtJawaban3.setText("");
            txtJawaban4.setText("");

            int height = 780;
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
        }
        if (indexCurrentQuestion == 3){
            int height = 380;
            int width = 380;

            txtJawaban1.setHeight(txtJawaban1.getLayoutParams().height = height);
            txtJawaban2.setHeight(txtJawaban2.getLayoutParams().height = height);
            txtJawaban3.setHeight(txtJawaban3.getLayoutParams().height = height);
            txtJawaban4.setVisibility(View.GONE);

            txtJawaban1.setWidth(txtJawaban1.getLayoutParams().width = width);
            txtJawaban2.setWidth(txtJawaban2.getLayoutParams().width = width);
            txtJawaban3.setWidth(txtJawaban3.getLayoutParams().width = width);

            jawaban1.setHeight(jawaban1.getLayoutParams().height = height);
            jawaban2.setHeight(jawaban2.getLayoutParams().height = height);
            jawaban3.setHeight(jawaban3.getLayoutParams().height = height);
            jawaban4.setVisibility(View.GONE);
        }

    }


    private void addQuestions() {
        questionsList.add(new SoalModel(
                "Pagi ini seperti biasa setelah bangun tidur, Andi akan mandi. Namun, Andi ingin bermain lompat-lompatan. \n" +
                        "\n" +
                        "Dibawah ini sudah ada kotak berisi barang barang, Andi dapat berhenti pada kotak yang berisikan peralatan mandi, dan harus melompat pada kotak yang tidak berisikan peralatan mandi. \n" +
                        "\n" +
                        "Berapa kotak Andi dapat berhenti (tidak melompat)?", R.drawable.gambar_soala1,
                "",
                "3 kali", 0,
                "2 kali", 0,
                "1 kali",0,
                "Melompat terus", 0,
                1));
        questionsList.add(new SoalModel(
                "Ani sedang bermain dengan Ayah menggunakan benda-benda di bawah ini.",R.drawable.gambar_soal_a2,
                "Ayah ingin benda berwarna hijau disusun diantara benda berwarna merah.\n" +
                        "\n" +
                        "Dapatkah kamu membantu Ani untuk menyusun benda tersebut?",
                "",R.drawable.images_opsi1_soala2,
                "",R.drawable.images_soala2_opsi2,
                "",R.drawable.images_soala2_opsi3,
                "",R.drawable.images_soala2_opsi4,
                4));
        questionsList.add(new SoalModel(
                "Anya mempunyai sebuah gelang, namun tiba-tiba gelang itu terputus. Ia ingin memperbaikinya.",R.drawable.img_soal_a3,
                "Bagaimana bentuk gelang setelah diperbaiki?",
                "",R.drawable.img_jawaban1_soala3,
                "",R.drawable.img_jawaban2_soala3,
                "",R.drawable.img_jawaban3_soala3,
                "",R.drawable.img_jawaban2_soala3,
                2
                ));
        questionsList.add(new SoalModel(
                "Bulan lalu saat Ulangan Akhir Semester, Ani mendapatkan hasil yang memuaskan. Ayah Ani berjanji akan membelikan Ani sepatu dan baju. \n" +
                        "\n" +
                        "Saat ini Ayah dan Ani sedang di pusat perbelanjaan. Ayah membebaskan Ani untuk membeli barang sesuai keinginannya. Ani menginginkan sepatu yang berbeda warna dengan baju. \n" +
                        "\n" +
                        "Jika ia membeli sepatu biru maka ia tidak akan memilih baju biru dan hijau. Jika Ia memilih sepatu kuning, ia tidak akan memilih baju berlengan panjang, pink, dan kuning. \n" +
                        "\n" +
                        "Berdasarkan daftar barang dibawah ini, manakah kemungkinan barang yang akan dibeli Ani jika ia menginginkan sepatu dan baju?",0,
                "",
                "",R.drawable.img_jawaban1_soala4,
                "",R.drawable.img_jawaban2_soala4,
                "",R.drawable.img_jawaban3_soala4,
                "",0,
                3));
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
                            int progressA = 100;
                            SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putInt("progressA", progressA);
                            editor.commit();
                            Intent intent = new Intent(SoalA.this, Kelas.class);
                            startActivity(intent);
                        }
                    });
                    }

            }
        });
    }
}