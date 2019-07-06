package com.fiek.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView fjalaTv, scoreTv;
    private EditText fjalaShtypurTv;
    private Button valido, lojeRe;
    private String fjalaMeGjet;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fjalaTv = (TextView) findViewById(R.id.fjalaTv);
        fjalaShtypurTv = (EditText) findViewById(R.id.fjalaShtypurEt);
        valido = (Button) findViewById(R.id.valido);
        valido.setOnClickListener(this);
        lojeRe = (Button) findViewById(R.id.lojeRe);
        lojeRe.setOnClickListener(this);

        lojeRe();
    }
    @Override
    public void onClick(View view) {
        if (view == valido) {
            valido();
        } else if (view == lojeRe) {
            lojeRe();
        }
    }
    private void valido() {
        String w = fjalaShtypurTv.getText().toString();

        if (fjalaMeGjet.equals(w)) {
            score++;
            Toast.makeText(this, "Urime! Ju e gjetet fjalen " + fjalaMeGjet, Toast.LENGTH_SHORT).show();
            lojeRe();

        } else {
            Toast.makeText(this, "Provoni perseri!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
    }
    private void lojeRe() {
        fjalaMeGjet = Anagram.randomFjala();
        String fjalaPerzier = Anagram.shuffleFjala(fjalaMeGjet);
        fjalaTv.setText(fjalaPerzier);
        fjalaShtypurTv.setText("");
    }
}