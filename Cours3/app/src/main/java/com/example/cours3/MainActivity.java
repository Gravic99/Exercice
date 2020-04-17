package com.example.cours3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if (item.getItemId()== R.id.mainMenu_SignOut){
            firebaseAuth.signOut();
            return true;
        }
        return false;

    }


    private void updateUI(FirebaseUser currentUser){
        if(currentUser == null){
            sendUserToSingUpOrLoginActivity();
        }
    }
    private void sendUserToSingUpOrLoginActivity(){
        Intent sendToSingUpOrLogInIntent =  new Intent(this,SingUpOrLoginActivity.class);
        startActivity(sendToSingUpOrLogInIntent);
    }


}
