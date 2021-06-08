package com.christerholmcode.duckforthebullet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Button startGameAgain = (Button) findViewById(R.id.play_again);
        startGameAgain.setOnClickListener(view -> {
            Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(mainIntent);
        });

    }
}




