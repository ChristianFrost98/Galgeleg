package com.example.myapplication.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.Model.GeussWord;
import com.example.myapplication.Model.HangmanModel;


import com.example.myapplication.Model.Level;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class HangmanLogic {

    private static SharedPreferences prefs;

    public static boolean isWordGuessed(){

        StringBuilder guessedword = new StringBuilder();
        for(int i=0;i<HangmanModel.geussWordList.size();i++){
            GeussWord currentGuessWord =  HangmanModel.geussWordList.get(i);
            if(currentGuessWord.isGeussed()){
                guessedword.append(currentGuessWord.getLetter().toString());
            }
        }
        return guessedword.toString().equals(HangmanModel.currentWord);
    }


    public static boolean wordContainLetter(String letter){
        boolean containsLetter = false;
        for(int i=0;i<HangmanModel.currentWord.length();i++){
            String letterInWord = String.valueOf(HangmanModel.currentWord.charAt(i));

            if(letter.equals(letterInWord)){
                HangmanModel.geussWordList.set(i,new GeussWord(letterInWord.charAt(0),true));
                containsLetter = true;
            }
        }
        return containsLetter;
    }



    public static void storePrefs(Context context){
        // Instantiate prefs
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        // Store level info
        Type levelListType = new TypeToken<ArrayList<Level>>(){}.getType();
        Gson gson = new Gson();
        String levelsJSON = gson.toJson(HangmanModel.levels, levelListType);
        prefs.edit().putString("levelsJSON", levelsJSON).apply();

    }

    public static void retrievePrefs(Context context){
        // Instantiate prefs
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        // Retrieve level info
        String levelsRetrieved = prefs.getString("levelsJSON","0");
        Gson gson = new Gson();
        Type levelListType = new TypeToken<ArrayList<Level>>(){}.getType();
        HangmanModel.levels = gson.fromJson(levelsRetrieved, levelListType);
    }



    public static void resetGame(){
        // Reset letters
        for(int i=0;i<HangmanModel.alphabet.size()-1;i++){
            HangmanModel.alphabet.get(i).setGeussed(false);
        }
        // Reset words
        for(int i=0;i<HangmanModel.geussWordList.size()-1;i++){
            HangmanModel.geussWordList.get(i).setGeussed(false);
        }

    }

}
