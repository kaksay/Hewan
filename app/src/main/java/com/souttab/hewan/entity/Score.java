package com.souttab.hewan.entity;

public class Score {

    private int _id;
    private String nama;
    private int score;
    private String scoreBy;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreBy() {
        return scoreBy;
    }

    public void setScoreBy(String scoreBy) {
        this.scoreBy = scoreBy;
    }
}
