package com.example.cours3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SingUpOrLoginActivity extends AppCompatActivity {
    Button goToLogInButton;
    Button goToSingUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_or_login);

        goToLogInButton = findViewById(R.id.btn_logIn);
        goToSingUpButton = findViewById(R.id.btn_signUp);

        setListener();
    }

    private void setListener(){
        goToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(LogInActivity.class);
            }
        });
        goToSingUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SignUpActivity.class);
            }
        });
    }

    private void goToActivity(Class activityToGo){
            Intent activity =  new Intent(this,activityToGo);
            startActivity(activity);

    }



}
