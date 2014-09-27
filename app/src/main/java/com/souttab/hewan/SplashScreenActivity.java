package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // tahan selama 8 detik, kemudian play suara hewannya
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                finish();
                startActivity(intent);

            }
        });
        // jalankan thread
        thread.start();

    }
}
