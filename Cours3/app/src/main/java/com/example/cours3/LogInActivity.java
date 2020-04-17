package com.example.cours3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LogInActivity extends AppCompatActivity {
   EditText emailEditText;
   EditText passwordEditText;
   Button btnLogin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEditText =  findViewById(R.id.editText_email);
        passwordEditText =  findViewById(R.id.editText_password);
        btnLogin.findViewById(R.id.btn_logIn);

        firebaseAuth = FirebaseAuth.getInstance();
        setListener();
    }
    private void setListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    showMessage("Hello there!");
                    sendToMainActivity();
                }else{
                    String message =  task.getException().toString();
                    showMessage(message);
                }
            }
        });
    }
    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void sendToMainActivity(){
        Intent activity =  new Intent(this,MainActivity.class);
        startActivity(activity);
    }


}
