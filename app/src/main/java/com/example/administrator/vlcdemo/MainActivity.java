package com.example.administrator.vlcdemo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private MyApplication app;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    private void initViews() {
//        path= Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + "/all_rise.mp3";


        AssetManager am = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open("all_rise.mp3");
            File file = createFileFromInputStream(inputStream);
            path = file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        Button load_a_mp3 = (Button) findViewById(R.id.load_a_mp3);
        load_a_mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMediaAtPath(path);
            }
        });

        Button loadVideo = (Button) findViewById(R.id.btnVideo);
        loadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntVideo= new Intent(MainActivity.this,VideoActivity.class);
                startActivity(IntVideo);
            }
        });

        RadioButton radioAudio = (RadioButton) findViewById(R.id.radioAudio);
        radioAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAdapter.setAudioMode(true);
//                mAdapter.refresh();
            }
        });
        RadioButton radioVideo = (RadioButton) findViewById(R.id.radioVideo);
        radioVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAdapter.setAudioMode(false);
//                mAdapter.refresh();
            }
        });

    }

    private void playMediaAtPath(String path) {
        // To play with LibVLC, we need a media player object.
        // Let's get one, if needed.
        app = (MyApplication) getApplication();
        MediaPlayer mMediaPlayer = app.getMediaPlayer();

        // Sanity check - make sure that the file exists.
        if (!new File(path).exists()) {
            Toast.makeText(
                    MainActivity.this,
                    path + " does not exist!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        // Create a new Media object for the file.
        // Each media - a song, video, or stream is represented by a Media object for LibVLC.
        Media m = new Media(app.getLibvlc(), path);

        // Tell the media player to play the new Media.
        mMediaPlayer.setMedia(m);

        // Finally, play it!
        mMediaPlayer.play();
    }

    private File createFileFromInputStream(InputStream inputStream) {

        try {
            File f = new File(getCacheDir() + "/output.mp3");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        } catch (IOException e) {
            //Logging exception
            e.printStackTrace();
        }

        return null;
    }

}
