package com.example.exercicecours1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GuessNumber guessNumber;

    private EditText inputNumberLeft;
    private EditText inputNumberRight;
    private Button buttonTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumberLeft = findViewById(R.id.editText_numberLeft);
        inputNumberRight = findViewById(R.id.editText_numberRight);
        buttonTry  = findViewById(R.id.button_Compare);
        guessNumber = new GuessNumber();
        setListenner();
    }
    private void setListenner(){
        buttonTry.setOnClickListener(new Button.OnClickListener() {
             @Override
             public void onClick(View view) {
                 compare();
             }
         });
    }
    private void compare(){
        int numberLeft = Integer.parseInt(inputNumberLeft.getText().toString());
        int numberRight = Integer.parseInt(inputNumberRight.getText().toString());

        GuessNumber.guessState result = guessNumber.CompareNumber(numberLeft,numberRight);

        if(result == GuessNumber.guessState.nbr1BIGGER){
            showCompare("Le nombre 1 est plus grand");
            inputNumberLeft.setBackgroundColor(0xFF00FF00);
            inputNumberRight.setBackgroundColor(0);
        }else if(result == GuessNumber.guessState.nbr2BIGGER){
            showCompare("Le nombre 2 est plus grand");
            inputNumberRight.setBackgroundColor(0xFF00FF00);
            inputNumberLeft.setBackgroundColor(0);
        }else{
            showCompare("Les nombres sont égaux");
            inputNumberLeft.setBackgroundColor(0);
            inputNumberRight.setBackgroundColor(0);
        }

    }
    private void showCompare(String compare)
    {
        Toast.makeText(this, compare, Toast.LENGTH_SHORT).show();
    }
}
