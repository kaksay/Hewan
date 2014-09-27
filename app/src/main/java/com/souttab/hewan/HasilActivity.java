package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.souttab.hewan.entity.UserName;
import com.souttab.hewan.util.KlikSuara;
import com.souttab.hewan.util.MyDatabase;
import com.souttab.hewan.util.TempScore;


public class HasilActivity extends Activity implements View.OnClickListener {

    private Button buttonMainLagi, buttonScore;
    private TextView textViewJawab, textViewScore;
    private String kuis;
    private MyDatabase database;
    private KlikSuara suara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hasil);

        // new database
        database = new MyDatabase(this);

        // suara klik
        suara = new KlikSuara(this);

        // reference variable
        startCom();

        // rubah font
        Typeface font = Typeface.createFromAsset(getAssets(), "Days.otf");
        textViewJawab.setTypeface(font);
        textViewScore.setTypeface(font);
        buttonScore.setTypeface(font);
        buttonMainLagi.setTypeface(font);

        // ambil variabel  yang di kirim ke class ini dari class sebelumnya
        String jawab = getIntent().getExtras().getString("jawab");
        kuis = getIntent().getExtras().getString("kuis");

        // cek jika jawab benar maka tampilkan
        // jawaban benar, jika salah maka tampilkan salah
        if (jawab.equals("benar") && kuis.equals("gambar")) {
            // set jawaban benar
            textViewJawab.setText(jawab);

            // panggil method tambah nilai
            TempScore.addScoreGambar();

            textViewScore.setText(TempScore.getScore_gambar() + "");
        } else if (jawab.equals("benar") && kuis.equals("suara")) {
            // set jawaban benar
            textViewJawab.setText(jawab);
            // panggil add nilai
            TempScore.addScoreSuara();

            // tampilan score
            textViewScore.setText(TempScore.getScore_suara() + "");
        } else {
            // jika jawaban salah maka set nilai 0
            textViewScore.setText("0");
            // ambil nilai tertinggi dan save ke database sebelum nilai di buat kosong
            if (kuis.equals("gambar")) {
                database.saveScore(UserName.getUsername(), TempScore.getScore_gambar(), kuis);
                TempScore.removeScoreGambar();
            } else if (kuis.equals("suara")) {
                database.saveScore(UserName.getUsername(), TempScore.getScore_suara(), kuis);
                TempScore.removeScoreSuara();
            }
            textViewJawab.setText("Jawaban Salah");


            // hapus nilai sementara yang ada
        }

        // klik button main lagi
        buttonMainLagi.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
    }

    /*
    * reference variable
    * */
    void startCom() {
        buttonMainLagi = (Button) findViewById(R.id.buttonMainLagi);
        buttonScore = (Button) findViewById(R.id.buttonScore);
        textViewJawab = (TextView) findViewById(R.id.textViewJawaban);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
    }

    /*
    * Click button implement
    * */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonMainLagi:
                suara.klik();
                // check terlebih dahulu activity apa yang sedang aktif
                // game gambar atau suara
                if (kuis.equals("gambar")) {
                    // acitivity gambar yang aktif
                    // panggil activitinya lagi ketika user panggil main lagi
                    intent = new Intent(HasilActivity.this, TebakGambarActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    // panggil actvity suara
                    intent = new Intent(HasilActivity.this, TebakSuaraActivity.class);
                    finish();
                    startActivity(intent);
                }
                break;
            case R.id.buttonScore:
                suara.klik();
                if (kuis.equals("gambar") && TempScore.getScore_gambar() != 0) {
                    database.saveScore(UserName.getUsername(), TempScore.getScore_gambar(), kuis);
                }

                if (kuis.equals("suara") && TempScore.getScore_suara() != 0) {
                    database.saveScore(UserName.getUsername(), TempScore.getScore_suara(), kuis);
                    Log.i("kuis", kuis);
                }

                // hapus nilai sementara yang ada
                TempScore.removeScoreGambar();
                TempScore.removeScoreSuara();

                intent = new Intent(HasilActivity.this, HighScoreActivity.class);
                intent.putExtra("kuis", kuis);
                finish();
                startActivity(intent);
            default:
                break;
        }
    }
}
