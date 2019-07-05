package com.fiek.androidapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fiek.androidapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    EditText editPassword, editUsername;
    Button buttonLogin;

    EditText editTextPhone, getEditTextCode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        editTextPhone = findViewById(R.id.editTextPhone);
        getEditTextCode = findViewById(R.id.editTextCode);



        findViewById(R.id.buttonGetCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });

        //firebase

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editPassword = (EditText) findViewById(R.id.editPassword);
        editUsername = (EditText) findViewById(R.id.editUsername);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(editUsername.getText().toString(), editPassword.getText().toString());
            }
        });
    }

    private void sendVerificationCode() {

        String phone = editTextPhone.getText().toString();

        if (phone.isEmpty()) {
            editTextPhone.setError("Kerkohet nr i telefonit");
            editTextPhone.requestFocus();
            return;
        }
    }

    private void signIn(final String username,final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(Login2.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(s);
                        }
                        else{
                            Toast.makeText(Login2.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(Login2.this, "Username is not registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}