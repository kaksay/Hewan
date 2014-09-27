package com.souttab.hewan.util;

public class TempScore {

    private static int score_gambar;
    private static int score_suara;

    public static void addScoreGambar() {
        score_gambar = score_gambar + 10;
    }

    public static void removeScoreGambar() {
        score_gambar = 0;
    }

    public static void addScoreSuara() {
        score_suara = score_suara + 10;
    }

    public static void removeScoreSuara() {
        score_suara = 0;
    }

    public static int getScore_gambar() {
        return score_gambar;
    }

    public static int getScore_suara() {
        return score_suara;
    }
}
