package com.fiek.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Rate extends AppCompatActivity {
    RatingBar ratingbar;
    Button button, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);
        btnBack=(Button)findViewById(R.id.btnBack);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent s = new Intent(getApplicationContext(), About.class);
                startActivity(s);
            }

        });
    }
}