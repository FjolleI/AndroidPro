package com.fiek.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    TextView tvHighScore, tvScore;
    Button btnRetry;
    int score;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gameoveractivity);

        tvHighScore = (TextView) findViewById(R.id.tvHighScore);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnRetry = (Button) findViewById(R.id.btnRetry);

        int score = getIntent().getIntExtra("SCORE", 0);
        tvScore.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highscore = settings.getInt("HIGH_SCOE", 0);

        if(score > highscore){
            tvHighScore.setText("High score : " + score);

            //Save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();

        }
else{
    tvHighScore.setText("High Score : "+highscore);
        }
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
