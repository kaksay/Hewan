package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.souttab.hewan.entity.GamesSuara;
import com.souttab.hewan.util.KlikSuara;
import com.souttab.hewan.util.MyDatabase;

import java.util.List;
import java.util.Random;

public class TebakSuaraActivity extends Activity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = null;
    private MediaPlayer mediaPlayer2 = null;
    private TextView textViewPertanyaan;
    private Button buttonA, buttonB, buttonC;
    private String jawabBenar;
    private KlikSuara suara;

    private MyDatabase database;

    // Untuk ambil nilai random
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = min + (int) (Math.random() * ((max - min) + 1));

        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak_suara);

        // reference variable
        startComp();

        // rubah font
        Typeface font = Typeface.createFromAsset(getAssets(), "Days.otf");
        buttonA.setTypeface(font);
        buttonB.setTypeface(font);
        buttonC.setTypeface(font);
        textViewPertanyaan.setTypeface(font);

        // database
        database = new MyDatabase(this);
        suara = new KlikSuara(this);
        // set data
        setData();

        // button on click
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);

    }

    void startComp() {
        textViewPertanyaan = (TextView) findViewById(R.id.textViewPertanyaanSuara);
        buttonA = (Button) findViewById(R.id.buttonASuara);
        buttonB = (Button) findViewById(R.id.buttonBSuara);
        buttonC = (Button) findViewById(R.id.buttonCSuara);
    }

    @Override
    protected void onPause() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            finish();
        }

        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onPause();
//        mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mediaPlayer.release();
//        mediaPlayer = null;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
            mediaPlayer2.release();
        }
    }

    void setData() {

        // ambil data dari database
        List<GamesSuara> suaraList = database.listSuara();

        // buat nilai random
        int randInt = randInt(0, 5);

        // ambil pertanyaan, pilihan jawaban, jawaban benar dan path suara
        // dari nilai random yang di dapat

        // ambil masing - masing nilai berdasarkan nilai random
        GamesSuara suara = suaraList.get(randInt);

        // ambil jawaban dan settext ke button pilihan
        // set pertanyaannya
        textViewPertanyaan.setText(suara.getPertanyaan());
        buttonA.setText(suara.getJawabA());
        buttonB.setText(suara.getJawabB());
        buttonC.setText(suara.getJawabC());

        // masukan jawaban benar biar di check dulu
        jawabBenar = suara.getJawabBenar();

        // ambil resID dari suara yang ada dalam folder raw
        Log.i("suara hewan", suara.getJawabBenar());
        Log.i("suaar pertanyaan", suara.getSuaraPertanyaan());

        final int resIdSuaraHewan = getResources().getIdentifier(suara.getJawabBenar(), "raw", getPackageName());
        int resIdSuaraPertanyaan = getResources().getIdentifier(suara.getSuaraPertanyaan(), "raw", getPackageName());

        // play suara pertanyaan
        mediaPlayer = MediaPlayer.create(this, resIdSuaraPertanyaan);
        mediaPlayer.start();

//        if (!mediaPlayer.isPlaying()) {
//            mediaPlayer = MediaPlayer.create(TebakSuaraActivity.this, resIdSuaraHewan);
//            mediaPlayer.start();
//        }

        // setelah suara pertanyaan selesai play suara hewannya
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // tahan selama 8 detik, kemudian play suara hewannya
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                check apakah media sedang play atau tidak,
//                masuan path suaranya yang diambil

                mediaPlayer2 = MediaPlayer.create(TebakSuaraActivity.this, resIdSuaraHewan);
                mediaPlayer2.start();

            }
        });
        // jalankan thread
        thread.start();
    }

    // method cek jawaban benar atau tidak
    void jawabBenar(String jawab) {
        Intent intent;
        if (jawab.equalsIgnoreCase(jawabBenar)) {
            Log.i("jawab", jawab);
            Log.i("Jawab benar", jawabBenar);
            intent = new Intent(TebakSuaraActivity.this, HasilActivity.class);
            intent.putExtra("kuis", "suara");
            intent.putExtra("jawab", "benar");
            mediaPlayer2.stop();
            finish();
            startActivity(intent);
        } else {
            intent = new Intent(TebakSuaraActivity.this, HasilActivity.class);
            intent.putExtra("kuis", "suara");
            intent.putExtra("jawab", "salah");
            mediaPlayer2.stop();
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonASuara:
                suara.klik();
                jawabBenar(buttonA.getText().toString());
                break;
            case R.id.buttonBSuara:
                suara.klik();
                jawabBenar(buttonB.getText().toString());
                break;
            case R.id.buttonCSuara:
                suara.klik();
                jawabBenar(buttonC.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();
        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();

        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
        }
        super.onDestroy();
    }
}
