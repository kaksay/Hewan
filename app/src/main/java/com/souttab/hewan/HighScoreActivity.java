package com.souttab.hewan;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.souttab.hewan.adapter.HighScoreAdapter;
import com.souttab.hewan.util.MyDatabase;

public class HighScoreActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_score);

        // ambil data yang dikirim dari activity sebelumnya
        String kuis = getIntent().getExtras().getString("kuis");

        Log.i("kuis", kuis + "");

        MyDatabase database = new MyDatabase(this);

        if (kuis.equals("gambar")) {
            setListAdapter(new HighScoreAdapter(this, database.listScoreGambar("gambar")));
        } else setListAdapter(new HighScoreAdapter(this, database.listScoreGambar("suara")));
    }


}
