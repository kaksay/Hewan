package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.souttab.hewan.entity.GamesGambar;
import com.souttab.hewan.util.ConvertImage;
import com.souttab.hewan.util.KlikSuara;
import com.souttab.hewan.util.MyDatabase;

import java.util.List;
import java.util.Random;

public class TebakGambarActivity extends Activity implements View.OnClickListener {

    private CircularImageView imageViewGambar;
    private TextView textViewPertanyaan;
    private Button buttonA, buttonB, buttonC;

    private MyDatabase database;

    private String jawabBenar;
    private KlikSuara suara;

    // Untuk ambil nilai random
    public static int randInt(int min, int max) {
        Random rand = new Random();

//        int randomNum = rand.nextInt((max - min) + 1) + min;
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));

        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tebak_gambar);

        // panggil reference
        onStartComp();

        // rubah font


        Typeface typeface = Typeface.createFromAsset(getAssets(), "Days.otf");
        textViewPertanyaan.setTypeface(typeface);
        buttonA.setTypeface(typeface);
        buttonB.setTypeface(typeface);
        buttonC.setTypeface(typeface);


        // initial database
        database = new MyDatabase(this);
        suara = new KlikSuara(this);

        // panggil set data
        setData();

        // onclik jawaban
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
    }

    // reference variable
    void onStartComp() {
        imageViewGambar = (CircularImageView) findViewById(R.id.imageViewTebakGambar);
        textViewPertanyaan = (TextView) findViewById(R.id.textViewPertanyaan);
        buttonA = (Button) findViewById(R.id.buttonJawabA);
        buttonB = (Button) findViewById(R.id.buttonJawabB);
        buttonC = (Button) findViewById(R.id.buttonJawabC);

    }

    // set data
    void setData() {
        // ambil data dari database
        List<GamesGambar> gamesGambars = database.kuisGambar();

        // tentukan nilai random
        int nilaiRandom = randInt(0, 1);

        // ambil data berdasarkan nilai random
        GamesGambar gamesGambar = gamesGambars.get(nilaiRandom);

        // set data yang telah di ambil
        // ke dalam layout
        imageViewGambar.setImageBitmap(ConvertImage.getImage(gamesGambar.getGambar()));
        textViewPertanyaan.setText(gamesGambar.getPertanyaan());
        buttonA.setText(gamesGambar.getJawab_a());
        buttonB.setText(gamesGambar.getJawab_b());
        buttonC.setText(gamesGambar.getJawab_c());

        // set jawaban benar
        jawabBenar = gamesGambar.getJawab_benar();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonJawabA:
                suara.klik();
                jawabBenar(buttonA.getText().toString());
                break;
            case R.id.buttonJawabB:
                suara.klik();
                jawabBenar(buttonB.getText().toString());
                break;
            case R.id.buttonJawabC:
                suara.klik();
                jawabBenar(buttonC.getText().toString());
                break;
            default:
                break;
        }
    }

    void jawabBenar(String jawab) {
        Intent intent;
        if (jawab.equals(jawabBenar)) {
            intent = new Intent(TebakGambarActivity.this, HasilActivity.class);
            intent.putExtra("kuis", "gambar");
            intent.putExtra("jawab", "benar");
            finish();
            startActivity(intent);
        } else {
            intent = new Intent(TebakGambarActivity.this, HasilActivity.class);
            intent.putExtra("kuis", "gambar");
            intent.putExtra("jawab", "salah");
            startActivity(intent);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setData();
    }
}
