package com.example.cours2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_VOLUME = 100;
    MediaPlayer mediaPlayer;
    Button btnPlay;
    Button btnPause;
    SeekBar soundProgressSeekBar;
    SeekBar volumeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay              = findViewById(R.id.btn_play);
        btnPause             = findViewById(R.id.btn_pause);
        soundProgressSeekBar = findViewById(R.id.seekBar_soundProgress);
        volumeSeekBar        = findViewById(R.id.seekBar_volume);
        mediaPlayer          = MediaPlayer.create(this, R.raw.takewhatyouwant);
        mediaPlayer.setVolume(soundProgressSeekBar.getProgress(), soundProgressSeekBar.getProgress());
        mediaPlayer.setLooping(false);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateProgressSoundSeekBar();
                handler.postDelayed(this, 1000);
            }
        }, 1000);

        setListener();
    }

    private void setListener(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMediaPlayer();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMediaPlayer();
            }
        });

        setSoundProgressSeekBarListener();

        setVolumeSeekBarListener();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                performOnEnd();
            }

        });
    }

    private void performOnEnd(){
        Toast.makeText(this, "Fin de l'audio!", Toast.LENGTH_SHORT).show();
    }

    private void setVolumeSeekBarListener(){
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeMediaVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changeMediaVolume(int progress){
        float volume = (float) (1 - (Math.log(MAX_VOLUME - progress) / Math.log(MAX_VOLUME)));
        mediaPlayer.setVolume(volume, volume);
    }

    private void setSoundProgressSeekBarListener(){
        soundProgressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    seekInMedia(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void seekInMedia(int progress){
        mediaPlayer.seekTo(progress * mediaPlayer.getDuration() / 100);
    }

    private void updateProgressSoundSeekBar(){
        soundProgressSeekBar.setProgress(mediaPlayer.getCurrentPosition() * 100 / mediaPlayer.getDuration());
    }

    private void playMediaPlayer(){
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    private void pauseMediaPlayer(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
}
