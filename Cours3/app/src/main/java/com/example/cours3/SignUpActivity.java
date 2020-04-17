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

public class SignUpActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    EditText ConfirmPasswordEditText;
    Button signUpButton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
         emailEditText = findViewById(R.id.editText_email);
         passwordEditText = findViewById(R.id.editText_password);
         ConfirmPasswordEditText = findViewById(R.id.editText_passwordConfirmation);
         signUpButton= findViewById(R.id.btn_singUp);
        setListener();
    }

    private void setListener(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }
    private void signUpUser(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = ConfirmPasswordEditText.getText().toString();
        if(!password.equals(confirmPassword)){
            showMessage("Password must match");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    showMessage("User created");
                    sendUserToMainActivity();
                }
                else{
                    String message = task.getException().toString();
                    showMessage(message);
                }
            }
        });

    }
    private void sendUserToMainActivity(){
        Intent sendToSingUpOrLogInIntent =  new Intent(this,MainActivity.class);
        startActivity(sendToSingUpOrLogInIntent);
    }
    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
