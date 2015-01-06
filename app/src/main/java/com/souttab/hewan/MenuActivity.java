package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.souttab.hewan.util.CopyDatabase;
import com.souttab.hewan.util.KlikSuara;

import java.sql.SQLException;

public class MenuActivity extends Activity implements View.OnClickListener {

    private KlikSuara suara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // buat jadi fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        CopyDatabase copyDatabase = new CopyDatabase(MenuActivity.this);
        try {
            copyDatabase.createdDatabase();
            copyDatabase.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        suara = new KlikSuara(this);

        Button buttonHewan = (Button) findViewById(R.id.buttonMenuHewan);
        Button buttonKuis = (Button) findViewById(R.id.buttonMenuKuis);
        Button buttonKeluar = (Button) findViewById(R.id.buttonMenuKeluar);


        // rubah type font
        // ambil font yang ada di asset
        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel_Regular.otf");
        // masukan ke button font yang telah di ambil
        buttonHewan.setTypeface(font);
        buttonKuis.setTypeface(font);
        buttonKeluar.setTypeface(font);

        // set action klik
        buttonHewan.setOnClickListener(this);
        buttonKuis.setOnClickListener(this);
        buttonKeluar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        switch (v.getId()) {
            // menuju hewan activity
            case R.id.buttonMenuHewan:
                intent = new Intent(getApplicationContext(), HewanActivity.class);
                suara.klik();
                startActivity(intent);
                break;
            // menuju kuis nama
            case R.id.buttonMenuKuis:
                intent = new Intent(getApplicationContext(), UserNameActivity.class);
                suara.klik();
                startActivity(intent);
                break;
            case R.id.buttonMenuKeluar:
                suara.klik();
                finish();
                break;
            default:
                break;
        }
    }


}
