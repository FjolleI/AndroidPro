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


        rlHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(About.this)
                        .setTitle("HELP")
                        .setMessage("Ju lutem prisni që të shfaqet një fjalë më shkronja të parenditura.\n" +
                                "\n" +
                                "Pasiqë të shfaqet anagrami ju duhet të shënoni fjalën e sakte që ju mendoni që e formojnë ato shkronja te parenditura.\n" +
                                "\n" +
                                "Pasiqë të shkruani fjalën që mendoni që është e saktë shtypni butonin \"VALIDO\"\n" +
                                "Nese nuk është e saktë ajo fjalë që keni shkruar atëherë ju duhet te provoni përsëri, e në rast se e qëlloni fjalën e saktë atëherë ju ndërrohet fjala dhe ju numërohet si 1 pikë.\n" +
                                "\n" +
                                "Nëse assesi nuk mund të qëlloni fjalën e kërkuar që e keni të shfaqur atëherë ju keni mundësinë të rifilloni edhe njëhërë nga fillimi duke shtypur butonin \"LOJË E RE\"\n")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        }) .show();
            }
        });

    }

}
