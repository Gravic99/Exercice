package com.example.exercicecours1;


public class GuessNumber {

    public enum guessState{nbr1BIGGER, EGUAL, nbr2BIGGER}

    public guessState CompareNumber(int nbr1, int nbr2){
        guessState numberState;
        if (nbr1 < nbr2){
            numberState =  guessState.nbr1BIGGER;
        }
        else if (nbr1 > nbr2){
            numberState = guessState.nbr2BIGGER;
        }
        else {
            numberState = guessState.EGUAL;
        }
        return numberState;
    }
}
