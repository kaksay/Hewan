package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.souttab.hewan.entity.UserName;
import com.souttab.hewan.util.KlikSuara;

public class UserNameActivity extends Activity {

    private EditText editTextNama;
    private Button buttonLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_username);

        startCom();
        final KlikSuara suara = new KlikSuara(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "KOMIKAX_.ttf");
        // masukan ke button font yang telah di ambil
        buttonLanjut.setTypeface(font);

        buttonLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add suara ketika di klik
                suara.klik();
                // ambil namanya
                // sebelumnya check apakah usernama kosong atau tidak
                try {
                    String username = editTextNama.getText().toString();
                    if (!username.trim().isEmpty()) {
                        UserName.setUsername(username);
                        Intent intent = new Intent(UserNameActivity.this, KuisActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        editTextNama.requestFocus();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void startCom() {
        editTextNama = (EditText) findViewById(R.id.editTextUserName);
        buttonLanjut = (Button) findViewById(R.id.buttonLanjut);
    }
}
