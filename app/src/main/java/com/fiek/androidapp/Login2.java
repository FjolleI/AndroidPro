package com.fiek.androidapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.androidapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class Login2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    EditText editPassword, editUsername;
    Button buttonLogin;

    EditText editTextPhone, getEditTextCode;
    FirebaseAuth mAuth;
    String codeSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        editTextPhone = findViewById(R.id.editTextPhone);
        getEditTextCode = findViewById(R.id.editTextCode);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonGetCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });



        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        editPassword = (EditText) findViewById(R.id.editPassword);
        editUsername = (EditText) findViewById(R.id.editUsername);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(editUsername.getText().toString(), editPassword.getText().toString());
                verifySignInCode();
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
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }
    private void verifySignInCode(){
        String code = getEditTextCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),"Incorrect verification code",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };
    private void signIn(final String username,final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(Login2.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            Intent s = new Intent(getApplicationContext(),Welcome.class);
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