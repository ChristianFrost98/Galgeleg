package com.example.myapplication.Logic;

import com.example.myapplication.HangmanState;
import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.PlayingState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HangmanLogic {

    HangmanModel hangmanModel = new HangmanModel();

    public static void initLevel(int level){
        // Set new word
        HangmanModel.currentWord = HangmanModel.possibleWords.get(level);
        // Set hashmap for geussing
        HashMap<Character, Boolean> newWord = new HashMap<>();
        for(int i = 0; i< HangmanModel.currentWord.length(); i++){
            newWord.put(HangmanModel.currentWord.charAt(i),false);
        }
        HangmanModel.currentGuessWord = newWord;

    }

    public static boolean wordGuessed(List<String> wordList, String word){
        for(Map.Entry<Character, Boolean> geussed : HangmanModel.currentGuessWord.entrySet()){
            if(!geussed.getValue()){
                return false;
            }
        }
        return true;
    }

    public static boolean wordContainLetter(String letter){
        boolean containsLetter = false;
        for(Map.Entry<Character, Boolean> geussed : HangmanModel.currentGuessWord.entrySet()){
            if(geussed.getKey() == letter.charAt(0)){
                geussed.setValue(true);
                containsLetter = true;
            }
        }
        return containsLetter;
    }




}
