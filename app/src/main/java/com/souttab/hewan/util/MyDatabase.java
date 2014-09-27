package com.souttab.hewan.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.souttab.hewan.entity.GamesGambar;
import com.souttab.hewan.entity.GamesSuara;
import com.souttab.hewan.entity.Hewan;
import com.souttab.hewan.entity.Score;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {
    private static final String _TABLE_HEWAN = "hewan";
    private static final String _TABLE_GAMBAR = "tebak_gambar";
    private static final String _SCORE = "score";
    private static final String DATABASE_NAME = "data";
    private static final int DATABASE_VERSION = 1;
    private static final String _TABLE_SUARA = "tebak_suara";
    private static final String _TABLE_SCORE = "score";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Hewan> listHewan() {
        List<Hewan> listHewan = new ArrayList<Hewan>();
        String query = "SELECT * FROM " + _TABLE_HEWAN;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                Hewan entity;
                do {
                    entity = new Hewan();
                    byte[] gambar = cursor.getBlob(cursor.getColumnIndex("gambar"));
                    String penjelasan = cursor.getString(cursor.getColumnIndex("penjelasan"));
                    String nama = cursor.getString(cursor.getColumnIndex("nama"));
                    String kelas = cursor.getString(cursor.getColumnIndex("kelas"));
                    int _id = cursor.getInt(cursor.getColumnIndex("_id"));

                    entity.set_id(_id);
                    entity.setNama(nama);
                    entity.setPenjelasan(penjelasan);
                    entity.setKelas(kelas);
                    entity.setGambar(gambar);

                    listHewan.add(entity);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }
        return listHewan;
    }

    public Hewan getHewan(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + _TABLE_HEWAN + " where _id=" + id, null);
        Hewan entity = null;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                entity = new Hewan();
                byte[] gambar = cursor.getBlob(cursor.getColumnIndex("gambar"));
                String penjelasan = cursor.getString(cursor.getColumnIndex("penjelasan"));
                String nama = cursor.getString(cursor.getColumnIndex("nama"));
                String kelas = cursor.getString(cursor.getColumnIndex("kelas"));
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String ordo = cursor.getString(cursor.getColumnIndex("ordo"));
                String family = cursor.getString(cursor.getColumnIndex("family"));
                String suara = cursor.getString(cursor.getColumnIndex("suara"));

                entity.setSuara(suara);
                entity.set_id(_id);
                entity.setNama(nama);
                entity.setPenjelasan(penjelasan);
                entity.setKelas(kelas);
                entity.setGambar(gambar);
                entity.setFamily(family);
                entity.setOrde(ordo);
            }
        } finally {
            database.close();
        }

        return entity;
    }

    public List<GamesGambar> kuisGambar() {
        List<GamesGambar> gamesGambars = new ArrayList<GamesGambar>();
        String query = "SELECT * FROM " + _TABLE_GAMBAR;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                GamesGambar game;
                do {
                    game = new GamesGambar();

                    game.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                    game.setPertanyaan(cursor.getString(cursor.getColumnIndex("pertanyaan")));
                    game.setJawab_a(cursor.getString(cursor.getColumnIndex("jawab_a")));
                    game.setJawab_b(cursor.getString(cursor.getColumnIndex("jawab_b")));
                    game.setJawab_c(cursor.getString(cursor.getColumnIndex("jawab_c")));
                    game.setJawab_benar(cursor.getString(cursor.getColumnIndex("jawab_benar")));
                    game.setGambar(cursor.getBlob(cursor.getColumnIndex("gambar")));

                    gamesGambars.add(game);
                } while (cursor.moveToNext());
            }
        } finally {
            close();
        }
        return gamesGambars;
    }

    public List<GamesSuara> listSuara() {
        List<GamesSuara> gamesSuara = new ArrayList<GamesSuara>();
        String query = "SELECT * FROM " + _TABLE_SUARA;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                GamesSuara suara;
                do {
                    suara = new GamesSuara();

                    suara.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                    suara.setPertanyaan(cursor.getString(cursor.getColumnIndex("pertanyaan")));
                    suara.setJawabA(cursor.getString(cursor.getColumnIndex("jawabA")));
                    suara.setJawabB(cursor.getString(cursor.getColumnIndex("jawabB")));
                    suara.setJawabC(cursor.getString(cursor.getColumnIndex("jawabC")));
                    suara.setJawabBenar(cursor.getString(cursor.getColumnIndex("jawab_benar")));
                    suara.setSuaraPertanyaan(cursor.getString(cursor.getColumnIndex("suara_pertanyaan")));

                    gamesSuara.add(suara);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }
        return gamesSuara;
    }

    public List<Score> listScoreGambar(String string) {
        List<Score> scores = new ArrayList<Score>();
        String query = " SELECT * FROM score where score_by = \"" + string + "\" order by score desc";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                Score score;
                do {
                    score = new Score();

                    score.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                    score.setNama(cursor.getString(cursor.getColumnIndex("nama")));
                    score.setScore(cursor.getInt(cursor.getColumnIndex("score")));

                    scores.add(score);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }

        return scores;

    }

    public void saveScore(String nama, int score, String scoreby) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("score", score);
        contentValues.put("score_by", scoreby);

        database.insert(_TABLE_SCORE, null, contentValues);

        database.close();


        Log.i("success", "success save data");

    }
}
