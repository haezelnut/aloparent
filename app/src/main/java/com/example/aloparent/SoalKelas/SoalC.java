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

import java.util.ArrayList;
import java.util.List;

public class SoalC extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(SoalC.this, Kelas.class);
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
        setContentView(R.layout.activity_soal_c);

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
                            int progressC = 100;
                            SharedPreferences mPrefs = getSharedPreferences("IDvalue3", 0);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putInt("progressC", progressC);
                            editor.commit();
                            Intent intent = new Intent(SoalC.this, Kelas.class);
                            startActivity(intent);
                        }
                    });
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
        if (indexCurrentQuestion == 1){
            int height = 280;
            int width = 280;

            txtJawaban1.setText("");
            txtJawaban2.setText("");
            txtJawaban3.setText("");
            txtJawaban4.setText("");

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
            int height = 120;
            int width = 400;

           /* ViewGroup.LayoutParams params = txtJawaban1.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            txtJawaban1.setLayoutParams(params); */

            txtJawaban1.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban2.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban3.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban4.setBackgroundResource(R.drawable.rectangle_edittext);

            txtJawaban1.setPadding(10,10,10,10);
            txtJawaban2.setPadding(10,10,10,10);
            txtJawaban3.setPadding(10,10,10,10);
            txtJawaban4.setPadding(10,10,10,10);

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

            int height = 80;
            int width = 400;


           /* ViewGroup.LayoutParams params = txtJawaban1.getLayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            txtJawaban1.setLayoutParams(params); */

            txtJawaban1.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban2.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban3.setBackgroundResource(R.drawable.rectangle_edittext);
            txtJawaban4.setBackgroundResource(R.drawable.rectangle_edittext);

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
                "Suatu hari, sebuah keluarga ingin mengadakan perjalanan liburan ke pantai. Karena jumlah anggota keluarga banyak, setiap orang diberi jatah maksimal 3 barang pribadi yang berbeda. Didi salah satu anggota keluarga tersebut bingung harus membawa barang bawaan apa. Ia berfikiran jika tidak harus membawa sandal. \n" +
                        "\n" +
                        "Dapatkah kalian membantu Didi menentukan barang bawaannya berdasarkan flow dibawah ini?", R.drawable.img_soalc1,
                "",
                "Kamera, kaos, sandal", 0,
                "Kamera, handuk, topi", 0,
                "Kamera, sandal, kaos",0,
                "Kamera, celana, kaos", 0,
                4));
        questionsList.add(new SoalModel(
                "Didi sedang memerhatikan gerakan ular di game miliknya. Tiba-tiba ada pertanyaan bagaimana gerakan ular ke-5. \n" +
                        "\n" +
                        "Dapatkah kamu membantu Didi untuk menemukan jawabannya?",R.drawable.img_soalc2,
                "",
                "",R.drawable.img_soalc2_opsi1,
                "",R.drawable.img_soalc2_opsi2,
                "",R.drawable.img_soalc2_opsi3,
                "",R.drawable.img_soalc2_opsi4,
                3));
        questionsList.add(new SoalModel(
                "Seekor katak berada diatas sebuah batu. Ia ingin menuju batu dekat daun hijau. Berdasarkan arah dan angka sesuai gambar, bagaimanakah urutan jalan katak sesuai dengan garis yang ada?  ",R.drawable.img_soalc3,
                "",
                "0 – 0 – 6 – 6 – 6 – 0 – 0 – 2 – 2 – 2 – 2 – 4 – 4 – 4",0,
                "6 – 6 – 4 – 4 – 4 – 4 – 2 – 4 – 1 – 1",0,
                "0 – 0 – 6 – 6 – 6 – 4 – 4 – 2 – 2 – 4 – 4 – 1",0,
                "4 – 1 – 0 – 0 – 0 – 6 – 6 – 4 – 4 – 2 – 3 – 1",0,
                3
        ));
        questionsList.add(new SoalModel(
                "Andi mempunyai gambar di bukunya. Ibu membantu Andi untuk mengelompokkan gambar makhluk hidup. Manakah gambar yang termasuk makhluk hidup?",R.drawable.img_soalc4,
                "",
                "1, 2, dan 3",0,
                "2, 3, dan 5",0,
                "3, 4, dan 5",0,
                "2, 3, dan 4",0,
                3));
    }
}