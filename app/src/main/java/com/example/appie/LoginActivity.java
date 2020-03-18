package com.example.appie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    EditText email, pass;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_field2);
        pass = findViewById(R.id.email_field2);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (myFirebaseUser != null){
                    Toast.makeText(LoginActivity.this, "You are logged in!", Toast.LENGTH_SHORT);
                    Intent i = new Intent(LoginActivity.this,  FunctionalityActivity.class);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please Login!", Toast.LENGTH_SHORT);
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = pass.getText().toString();
                if(e.isEmpty() && p.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fields are empty!", Toast.LENGTH_SHORT);
                }
                else if (p.isEmpty()){
                    pass.setError("Please enter your password!");
                    pass.requestFocus();
                }
                else if(e.isEmpty()){
                    email.setError("Please enter your email!");
                    email.requestFocus();
                }
                else if(!e.isEmpty() && !p.isEmpty()){
                    System.out.println("LOOL");
                    myFirebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error! Please try again!", Toast.LENGTH_SHORT);
                            }
                            else{
                                Intent i = new Intent(LoginActivity.this, FunctionalityActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
