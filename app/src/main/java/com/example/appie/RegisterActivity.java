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

public class RegisterActivity extends AppCompatActivity {

    //instance fields of the class
    private FirebaseAuth myFirebaseAuth;
    private EditText email, pass;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //intiialize myFirebaseAuth
        myFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_field);
        pass = findViewById(R.id.email_field);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = pass.getText().toString();
                if(e.isEmpty() && p.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fields are empty!", Toast.LENGTH_SHORT);
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
                    myFirebaseAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registration is unsuccessful! Please try again!", Toast.LENGTH_SHORT);
                            }
                            else {
                                System.out.println("LOOL");
                                startActivity(new Intent(RegisterActivity.this, FunctionalityActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT);

                }
            }
        });
    }
}
