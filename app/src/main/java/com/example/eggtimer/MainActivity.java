package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView time_text;
    SeekBar seekBar;
    boolean stop =false;
    int time;
    CountDownTimer timer;
    MediaPlayer mediaPlayer;
    public void OnStart(View view){
        stop = false;
        timer = new CountDownTimer(time*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long remaining = millisUntilFinished/1000;
                Log.i("Button Pressed:",Long.toString(remaining));
                long minutes = remaining/60;
                long seconds = remaining%60;
                String time_str = String.format("%02d:%02d", minutes, seconds);
                time_text.setText(time_str);
                seekBar.setProgress((int)remaining);
                if(stop){
                    timer.cancel();
//                    time_text.setText("00:00");
//                    time=00;
                }
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
                Log.i("Timer Finished: ","Thanks");
            }
        };
        timer.start();
        seekBar.setEnabled(false);
    }
    public void OnStop(View view) {
        seekBar.setEnabled(true);
        stop = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        time_text = findViewById(R.id.textView);
        Button stop = findViewById(R.id.stop_button);
        Button start = findViewById(R.id.start_Button);
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
//        int time = 31;
//        int hours = time/60;
//        int minutes = time%60;
//        String time_str = String.format("%02d:%02d", hours, minutes);
//        time_text.setText(time_str);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.i("Progress: ", Integer.toString(progress));
                time = progress;
                int minutes = time/60;
                int seconds = time%60;
                String time_str = String.format("%02d:%02d", minutes, seconds);
                time_text.setText(time_str);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}