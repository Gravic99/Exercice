package com.example.cours3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cours3.model.MessageModel;
import com.example.cours3.notification.NotificationService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database;

    EditText messageEditText;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        messageEditText = findViewById(R.id.editText_message);
        btnSend = findViewById(R.id.btn_sendMessage);
        setListener();

    }

    private void setListener(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendMessage();
            }
        });
    }

    private void sendMessage(){
        // Toast.makeText(this,"notification test",Toast.LENGTH_SHORT).show();
        String sender = firebaseAuth.getCurrentUser().getEmail();
        String messageToSend = messageEditText.getText().toString();
        MessageModel messageModel = new MessageModel(messageToSend,sender,false);
        database.collection("Notification").add(messageModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Message Send",Toast.LENGTH_SHORT).show();
            }
        });

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
        }else{
            startService();
        }
    }
    private void sendUserToSingUpOrLoginActivity(){
        Intent sendToSingUpOrLogInIntent =  new Intent(this,SingUpOrLoginActivity.class);
        startActivity(sendToSingUpOrLogInIntent);
    }

    private void startService(){
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this,serviceIntent);
    }

}
