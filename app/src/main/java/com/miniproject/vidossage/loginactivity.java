package com.miniproject.vidossage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {


    EditText emailBox, pwdbox;
    Button loginBtn, signupBtn;

    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        emailBox = findViewById(R.id.emailbox);
        pwdbox = findViewById(R.id.pwdbox);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait......");
        firebaseAuth = FirebaseAuth.getInstance();

        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.createbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email, password;
                email = emailBox.getText().toString();
                password = pwdbox.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(loginactivity.this, Dashboard.class));
                        }
                        else {
                            Toast.makeText(loginactivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginactivity.this, signupActivity.class));
            }
        });
    }
}