package com.example.cours5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PasswordView passwordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordView = findViewById(R.id.passwordView);
        setListener();
    }
    private void setListener(){
        passwordView.setOnGoodPasswordListener(new PasswordView.OnGoodPasswordListener() {
            @Override
            public void onGoodPassword() {
                Toast.makeText(getApplicationContext(),"Bon mot de passe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
