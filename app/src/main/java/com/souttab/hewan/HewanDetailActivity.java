package com.souttab.hewan;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.souttab.hewan.entity.Hewan;
import com.souttab.hewan.util.MyDatabase;

import pl.droidsonroids.gif.GifImageView;

public class HewanDetailActivity extends Activity {

    TextView textViewPenjelasan;
    GifImageView imageViewGambar;
    ImageView imageViewSuara, imageViewSuaraPenjelasan;
    MediaPlayer mediaPlayer;
    MyDatabase database;
    String suara, namaHewan;
    int resIdSuaraHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hewan_detail);

        // reference variable
        onCreatedComp();

        // inicial database
        database = new MyDatabase(this);

        // getId dari layout sebelumnya
        int id = getIntent().getExtras().getInt("id");

        // rubah font
        Typeface font = Typeface.createFromAsset(getAssets(), "Action_Man.ttf");
        textViewPenjelasan.setTypeface(font);

        // set data ke layout
        setData(id);
        if (!suara.isEmpty()) {
            resIdSuaraHewan = getResources().getIdentifier(suara, "raw", getPackageName());
            Log.i("suara", suara);
        }

        // suara hewan
        imageViewSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), resIdSuaraHewan);
                mediaPlayer.start();
            }
        });


        // check nama hewan
        final int reIdSuaraPenjelasan = getResources().getIdentifier("d_"+suara, "raw", getPackageName());
        // suara penjelasan hewan
        imageViewSuaraPenjelasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), reIdSuaraPenjelasan);
                mediaPlayer.start();
            }
        });

    }

    void onCreatedComp() {
        imageViewSuara = (ImageView) findViewById(R.id.imageButtonSuara);
        imageViewGambar = (GifImageView) findViewById(R.id.imageViewGambarDetail);
        textViewPenjelasan = (TextView) findViewById(R.id.textViewPenjelasanDetail);
        imageViewSuaraPenjelasan = (ImageView) findViewById(R.id.imageButtonSuaraPenjelasan);

    }

    // untuk masukin data ke layout
    void setData(int id) {
        // ambil data dari database
        Hewan hewan = database.getHewan(id);

        final int gambar = getResources().getIdentifier(hewan.getSuara(), "drawable", getPackageName());

        // set data ke variable di layout
        // imageViewGambar.setImageBitmap(ConvertImage.getImage(hewan.getGambar()));
        imageViewGambar.setImageResource(gambar);
        suara = hewan.getSuara();

        // suara penjelasan hewan
        namaHewan = hewan.getNama();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        super.onBackPressed();
    }
}
