package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button start, stop, restart;
    TextView textView;
    private int seconds =0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        if(savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running =savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }//end if
        runTime();
    }//end onCreate()

    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }//onSaveInstanceState()

    public void onClickStart(View view){
        running = true;

    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickRestart(View view){
        running = false;
        seconds = 0;
    }

    private void runTime(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d : %02d : %02d", hours, minutes, secs);
                textView.setText(time);
                if (running){
                    seconds++;
                }//end if()
                handler.postDelayed(this, 1000);
            }
        });
    }//end runTime()

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }




}//end class