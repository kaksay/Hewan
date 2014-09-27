package com.souttab.hewan.entity;

public class GamesGambar {

    private int _id;
    private String pertanyaan;
    private String jawab_a;
    private String jawab_b;
    private String jawab_c;
    private String jawab_benar;
    private byte[] gambar;

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawab_a() {
        return jawab_a;
    }

    public void setJawab_a(String jawab_a) {
        this.jawab_a = jawab_a;
    }

    public String getJawab_b() {
        return jawab_b;
    }

    public void setJawab_b(String jawab_b) {
        this.jawab_b = jawab_b;
    }

    public String getJawab_c() {
        return jawab_c;
    }

    public void setJawab_c(String jawab_c) {
        this.jawab_c = jawab_c;
    }

    public String getJawab_benar() {
        return jawab_benar;
    }

    public void setJawab_benar(String jawab_benar) {
        this.jawab_benar = jawab_benar;
    }
}
