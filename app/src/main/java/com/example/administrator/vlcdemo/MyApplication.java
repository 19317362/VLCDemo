package com.example.administrator.vlcdemo;

import android.app.Application;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MyApplication extends Application {

    // media player
    private static LibVLC libvlc = null;
    private static MediaPlayer mMediaPlayer = null;

    @Override
    public void onCreate() {
        super.onCreate();
        getLibvlc();
        getMediaPlayer();
    }

    public LibVLC getLibvlc() {
        if (libvlc == null) {
            ArrayList<String> options = new ArrayList<String>();
            //options.add("--subsdec-encoding <encoding>");
            options.add("--aout=opensles");
            options.add("--audio-time-stretch"); // time stretching
            options.add("-vvv"); // verbosity
            libvlc = new LibVLC(options);
        }
        return libvlc;
    }

    public MediaPlayer getMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer(getLibvlc());
        }
        return mMediaPlayer;
    }
}
