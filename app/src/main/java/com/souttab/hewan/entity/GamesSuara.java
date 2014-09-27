package com.souttab.hewan.entity;

public class GamesSuara {

    private int _id;
    private String pertanyaan;
    private String jawabA;
    private String jawabB;
    private String jawabC;
    private String jawabBenar;
    private String suaraPertanyaan;

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

    public String getJawabA() {
        return jawabA;
    }

    public void setJawabA(String jawabA) {
        this.jawabA = jawabA;
    }

    public String getJawabB() {
        return jawabB;
    }

    public void setJawabB(String jawabB) {
        this.jawabB = jawabB;
    }

    public String getJawabC() {
        return jawabC;
    }

    public void setJawabC(String jawabC) {
        this.jawabC = jawabC;
    }

    public String getJawabBenar() {
        return jawabBenar;
    }

    public void setJawabBenar(String jawabBenar) {
        this.jawabBenar = jawabBenar;
    }

    public String getSuaraPertanyaan() {
        return suaraPertanyaan;
    }

    public void setSuaraPertanyaan(String suaraPertanyaan) {
        this.suaraPertanyaan = suaraPertanyaan;
    }
}
