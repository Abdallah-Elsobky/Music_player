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
    boolean isrepeat=false;
    boolean isshuffle=false;
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
        MediaPlayer click = MediaPlayer.create(MainActivity.this,R.raw.click3);
        MediaPlayer mp3 = MediaPlayer.create(MainActivity.this,R.raw.quran);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
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
                click.start();
                if(isrepeat){
                    repeat.setImageResource(R.drawable.nonrepeat);
                }else{
                    repeat.setImageResource(R.drawable.repeat1);
                }
                isrepeat = !isrepeat;
            }
        });
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                if(isshuffle){
                    shuffle.setImageResource(R.drawable.shuffle);
                }else{
                    shuffle.setImageResource(R.drawable.change);
                }
                isshuffle = !isshuffle;
            }
        });
        mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(isrepeat){
                    mp3.start();
                }else{
                    play.setImageResource(R.drawable.playbuttton);
                }
            }
        });
    }
}