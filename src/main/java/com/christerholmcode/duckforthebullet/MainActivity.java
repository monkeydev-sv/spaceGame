package com.christerholmcode.duckforthebullet;


import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private FlyingShipView gameView;
private Handler handler = new Handler();
private final static long interval = 30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new FlyingShipView(this);
        setContentView(gameView);
Timer timer = new Timer();
timer.schedule(new TimerTask() {
    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run()
            {
            gameView.invalidate();
            }
        });
    }
},0,interval);

    }

}

