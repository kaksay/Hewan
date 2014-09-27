package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.souttab.hewan.util.KlikSuara;

public class KuisActivity extends Activity implements View.OnClickListener {

    private KlikSuara suara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_kuis);

        // create suara
        suara = new KlikSuara(this);

        TextView textView = (TextView) findViewById(R.id.textViewPilihGameMu);
        Button buttonTebakGambar = (Button) findViewById(R.id.buttonTebakGambar);
        Button buttonTebakSuara = (Button) findViewById(R.id.buttonTebakSuara);

        Typeface font = Typeface.createFromAsset(getAssets(), "KOMIKAX_.ttf");
        // masukan ke button font yang telah di ambil
        textView.setTypeface(font);
        buttonTebakGambar.setTypeface(font);
        buttonTebakSuara.setTypeface(font);

        buttonTebakGambar.setOnClickListener(this);
        buttonTebakSuara.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonTebakGambar:
                intent = new Intent(KuisActivity.this, TebakGambarActivity.class);
                suara.klik();
                finish();
                startActivity(intent);
                break;
            case R.id.buttonTebakSuara:
                intent = new Intent(KuisActivity.this, TebakSuaraActivity.class);
                suara.klik();
                finish();
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
