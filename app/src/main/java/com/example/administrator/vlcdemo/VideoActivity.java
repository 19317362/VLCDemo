package com.example.administrator.vlcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoActivity extends AppCompatActivity {


    // display surface
    private SurfaceView mSurface;
    private SurfaceHolder holder;

    private int mVideoWidth;
    private int mVideoHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }
}
