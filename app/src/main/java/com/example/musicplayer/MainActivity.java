package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton play;
    ImageButton lnext;
    ImageButton rnext;
    ImageButton repeat;
    ImageButton shuffle;
    boolean isrepeat=true;
    boolean isshuffle=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play);
        lnext = findViewById(R.id.lnext);
        rnext = findViewById(R.id.rnext);
        repeat = findViewById(R.id.repeat);
        shuffle = findViewById(R.id.shuffle);
        MediaPlayer mp3 = MediaPlayer.create(MainActivity.this,R.raw.taha);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mp3.isPlaying()){
                    play.setImageResource(R.drawable.pause);
                    mp3.start();
                }else{
                    play.setImageResource(R.drawable.playbuttton);
                    mp3.pause();
                }
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isrepeat){
                    repeat.setImageResource(R.drawable.repeat1);
                }else{
                    repeat.setImageResource(R.drawable.nonrepeat);
                }
                isrepeat = !isrepeat;
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isshuffle){
                    shuffle.setImageResource(R.drawable.change);
                }else{
                    shuffle.setImageResource(R.drawable.shuffle);
                }
                isshuffle = !isshuffle;
            }
        });
    }
}