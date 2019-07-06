package com.fiek.androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.androidapp.R;


public class About extends AppCompatActivity {
    RelativeLayout rlRate,rlFeedback, rlHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        rlRate = (RelativeLayout) findViewById(R.id.rlRate);
        rlFeedback = (RelativeLayout) findViewById(R.id.rlFeedback);
        rlHelp = (RelativeLayout) findViewById(R.id.rlHelp);
        rlRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), Rate.class);
                startActivity(s);
                Toast.makeText(getApplicationContext(),"Rate",Toast.LENGTH_LONG).show();
            }
        });




        rlFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), Feedback.class);
                startActivity(s);
                Toast.makeText(getApplicationContext(),"Feedback",Toast.LENGTH_LONG).show();
            }
        });