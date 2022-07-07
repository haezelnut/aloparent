package com.example.aloparent.SoalKelas;

public class SoalModel {
    private String pertanyaan, jawaban1, jawaban2, jawaban3, jawaban4;
    private int imgPertanyaan, imgJawaban1, imgJawaban2, imgJawaban3, imgJawaban4;
    private int kunciJawaban;

    public SoalModel() {}

    public SoalModel(String pertanyaan,int imgPertanyaan, String jawaban1,int imgJawaban1, String jawaban2,int imgJawaban2, String jawaban3,int imgJawaban3, String jawaban4,  int imgJawaban4, int kunciJawaban) {
        this.pertanyaan = pertanyaan;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.imgPertanyaan = imgPertanyaan;
        this.imgJawaban1 = imgJawaban1;
        this.imgJawaban2 = imgJawaban2;
        this.imgJawaban3 = imgJawaban3;
        this.imgJawaban4 = imgJawaban4;
        this.kunciJawaban = kunciJawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban1() {
        return jawaban1;
    }

    public void setJawaban1(String jawaban1) {
        this.jawaban1 = jawaban1;
    }

    public String getJawaban2() {
        return jawaban2;
    }

    public void setJawaban2(String jawaban2) {
        this.jawaban2 = jawaban2;
    }

    public String getJawaban3() {
        return jawaban3;
    }

    public void setJawaban3(String jawaban3) {
        this.jawaban3 = jawaban3;
    }

    public String getJawaban4() {
        return jawaban4;
    }

    public void setJawaban4(String jawaban4) {
        this.jawaban4 = jawaban4;
    }

    public int getImgPertanyaan() {
        return imgPertanyaan;
    }

    public void setImgPertanyaan(int imgPertanyaan) {
        this.imgPertanyaan = imgPertanyaan;
    }

    public int getImgJawaban1() {
        return imgJawaban1;
    }

    public void setImgJawaban1(int imgJawaban1) {
        this.imgJawaban1 = imgJawaban1;
    }

    public int getImgJawaban2() {
        return imgJawaban2;
    }

    public void setImgJawaban2(int imgJawaban2) {
        this.imgJawaban2 = imgJawaban2;
    }

    public int getImgJawaban3() {
        return imgJawaban3;
    }

    public void setImgJawaban3(int imgJawaban3) {
        this.imgJawaban3 = imgJawaban3;
    }

    public int getImgJawaban4() {
        return imgJawaban4;
    }

    public void setImgJawaban4(int imgJawaban4) {
        this.imgJawaban4 = imgJawaban4;
    }

    public int getKunciJawaban() {
        return kunciJawaban;
    }

    public void setKunciJawaban(int kunciJawaban) {
        this.kunciJawaban = kunciJawaban;
    }
}
