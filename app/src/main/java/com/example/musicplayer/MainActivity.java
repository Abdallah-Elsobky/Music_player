package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.R;

public class MainActivity extends AppCompatActivity {

    ImageButton play, lnext, rnext, repeat, shuffle;
    SeekBar seekBar;
    boolean isrepeat = false;
    boolean isshuffle = false;
    MediaPlayer mp3, click;
    Handler handler = new Handler();

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
        seekBar = findViewById(R.id.seekBar);

        click = MediaPlayer.create(MainActivity.this, R.raw.click3);
        mp3 = MediaPlayer.create(MainActivity.this, R.raw.quran);

        seekBar.setMax(mp3.getDuration());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                if (!mp3.isPlaying()) {
                    play.setImageResource(R.drawable.pause);
                    mp3.start();
                    updateSeekBar();
                } else {
                    play.setImageResource(R.drawable.playbuttton);
                    mp3.pause();
                }
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                if (isrepeat) {
                    repeat.setImageResource(R.drawable.nonrepeat);
                } else {
                    repeat.setImageResource(R.drawable.repeat1);
                }
                isrepeat = !isrepeat;
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.start();
                if (isshuffle) {
                    shuffle.setImageResource(R.drawable.shuffle);
                } else {
                    shuffle.setImageResource(R.drawable.change);
                }
                isshuffle = !isshuffle;
            }
        });

        mp3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (isrepeat) {
                    mp3.start();
                } else {
                    play.setImageResource(R.drawable.playbuttton);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mp3.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something when touch starts
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something when touch stops
            }
        });
    }

    private void updateSeekBar() {
        seekBar.setProgress(mp3.getCurrentPosition());
        if (mp3.isPlaying()) {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                }
            };
            handler.postDelayed(updater, 50);
        }
    }
}
