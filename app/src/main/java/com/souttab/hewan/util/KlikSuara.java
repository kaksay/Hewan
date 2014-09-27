package com.souttab.hewan.util;

import android.content.Context;
import android.media.MediaPlayer;

import com.souttab.hewan.R;

public class KlikSuara {

    private Context context;
    private MediaPlayer mediaPlayer;

    public KlikSuara(Context mContext) {
        this.context = mContext;
        mediaPlayer = MediaPlayer.create(context, R.raw.klik);
    }


    public void klik() {
        mediaPlayer.start();
    }
}
