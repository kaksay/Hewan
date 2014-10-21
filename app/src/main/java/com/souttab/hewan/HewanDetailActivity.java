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

    TextView textViewPenjelasan, textViewNama, textViewOrdo, textViewFamily, textViewKelas;
    GifImageView imageViewGambar;
    ImageView imageViewSuara;
    MediaPlayer mediaPlayer;
    MyDatabase database;
    String suara;
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
        textViewFamily.setTypeface(font);
        textViewKelas.setTypeface(font);
        textViewNama.setTypeface(font);
        textViewOrdo.setTypeface(font);
        textViewPenjelasan.setTypeface(font);
        // set data ke layout
        setData(id);
        if (!suara.isEmpty()) {
            resIdSuaraHewan = getResources().getIdentifier(suara, "raw", getPackageName());
            Log.i("suara", suara);
        }

        imageViewSuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = MediaPlayer.create(getApplicationContext(), resIdSuaraHewan);
                mediaPlayer.start();
            }
        });

    }

    void onCreatedComp() {
        imageViewSuara = (ImageView) findViewById(R.id.imageButtonSuara);
        imageViewGambar = (GifImageView) findViewById(R.id.imageViewGambarDetail);
        textViewPenjelasan = (TextView) findViewById(R.id.textViewPenjelasanDetail);
        textViewNama = (TextView) findViewById(R.id.textViewNamaHewanDetail);
        textViewOrdo = (TextView) findViewById(R.id.textViewOrdoDetail);
        textViewFamily = (TextView) findViewById(R.id.textViewFamilyDetail);
        textViewKelas = (TextView) findViewById(R.id.textViewKelasDetail);
    }

    // untuk masukin data ke layout
    void setData(int id) {
        // ambil data dari database
        Hewan hewan = database.getHewan(id);

        final int gambar = getResources().getIdentifier(hewan.getSuara(), "drawable", getPackageName());

        // set data ke variable di layout
//        imageViewGambar.setImageBitmap(ConvertImage.getImage(hewan.getGambar()));
        imageViewGambar.setImageResource(gambar);
        textViewPenjelasan.setText("Penjelasan : \n" + hewan.getPenjelasan());
        textViewNama.setText("Nama : " + hewan.getNama());
        textViewOrdo.setText("Ordo : " + hewan.getOrde());
        textViewFamily.setText("Family : " + hewan.getFamily());
        textViewKelas.setText("Kelas : " + hewan.getKelas());
        suara = hewan.getSuara();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        super.onPause();
    }
}
