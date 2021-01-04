package com.example.myapplication.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.example.myapplication.Model.HangmanModel;


import com.example.myapplication.Model.Letter;
import com.example.myapplication.Model.Level;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HangmanLogic {

    public static boolean isWordGuessed(){
        StringBuilder guessedword = new StringBuilder();
        for(int i=0;i<HangmanModel.geussWordList.size();i++){
            Letter currentGuessWord =  HangmanModel.geussWordList.get(i);
            if(currentGuessWord.isGeussed()){
                guessedword.append(currentGuessWord.getLetter().toString());
            }
        }
        return guessedword.toString().equals(HangmanModel.currentWord.toUpperCase());
    }


    public static boolean wordContainLetter(String letter){
        boolean containsLetter = false;
        for(int i=0;i<HangmanModel.currentWord.length();i++){
            String letterInWord = String.valueOf(HangmanModel.currentWord.charAt(i)).toUpperCase();

            if(letter.toUpperCase().equals(letterInWord)){
                HangmanModel.geussWordList.set(i,new Letter(letterInWord.charAt(0),true));
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


    public static void retrievePrefs(Context context) throws IOException {
        // Instantiate prefs
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        // Retrieve level info
        String levelsRetrieved = prefs.getString("levelsJSON","0");
        if(levelsRetrieved.equals("0") || HangmanModel.justResat){
            initArrays();
        } else {
            Gson gson = new Gson();
            Type levelListType = new TypeToken<ArrayList<Level>>(){}.getType();
            HangmanModel.levels = gson.fromJson(levelsRetrieved, levelListType);
        }
    }


    public static void resetGame(){
        // Reset letters
        for(int i=0;i<HangmanModel.alphabet.size();i++){
            HangmanModel.alphabet.get(i).setGeussed(false);
        }
        // Reset words
        for(int i=0;i<HangmanModel.geussWordList.size()-1;i++){
            HangmanModel.geussWordList.get(i).setGeussed(false);
        }
    }


    public static String getURLData(String urlString) {
        try  {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(urlString).openStream()));
            StringBuilder sb = new StringBuilder();
            String linje = br.readLine();
            while (linje != null) {
                sb.append(linje + "\n");
                linje = br.readLine();
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<String> retrieveWords() throws IOException {
        List<String> words;
        String data = getURLData("https://nyheder.tv2.dk/");

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        words = Arrays.asList(data.split("\\s+"));
        return words;
    }

    public static int randomWord(List<String> words){
        Random r = new Random();
        int low = 200;
        int high = words.size();
        return r.nextInt(high-low) + low;
    }

    public static void initArrays() throws IOException {
        // Clear if exiting array - for the reset
        if(HangmanModel.levels.size() != 0){
            HangmanModel.levels.clear();
        }

        // If just resat, from flag
        HangmanModel.justResat = false;

        // Retrieved words
        List<String> words = retrieveWords();

        // Generate random number - the starting point
        int count = randomWord(words);

        // Make 20 levels, from random starting point in words list
        if(words.size() != 0) {
            int i = 0;
            while (HangmanModel.levels.size() < 20){
                if(!(words.get(count).equals(""))){
                    HangmanModel.levels.add(i, new Level(String.valueOf(i+1), false, words.get(count), new ArrayList<Integer>()));
                    i++;
                }
                count = randomWord(words);
            }
        } else {
            // Add to levels, if no data is retrieved
            HangmanModel.levels.add(0,new Level("1",false,"BIL",new ArrayList<Integer>()));
            HangmanModel.levels.add(1,new Level("2",false,"COMPUTER",new ArrayList<Integer>()));
            HangmanModel.levels.add(2,new Level("3",false,"MOTORVEJ",new ArrayList<Integer>()));
            HangmanModel.levels.add(3,new Level("4",false,"BUSRUTE",new ArrayList<Integer>()));
            HangmanModel.levels.add(4,new Level("5",false,"GANGSTI",new ArrayList<Integer>()));
            HangmanModel.levels.add(5,new Level("6",false,"SKOVSNEGEL",new ArrayList<Integer>()));
            HangmanModel.levels.add(6,new Level("7",false,"SOLSORT",new ArrayList<Integer>()));
            HangmanModel.levels.add(7,new Level("8",false,"TYVE",new ArrayList<Integer>()));
            HangmanModel.levels.add(8,new Level("9",false,"PIVOTERE",new ArrayList<Integer>()));
            HangmanModel.levels.add(9,new Level("10",false,"OVERGANG",new ArrayList<Integer>()));
            HangmanModel.levels.add(10,new Level("11",false,"SMOVS",new ArrayList<Integer>()));
            HangmanModel.levels.add(11,new Level("12",false,"NÆSEKLEMME",new ArrayList<Integer>()));
            HangmanModel.levels.add(12,new Level("13",false,"NITTENÅRIG",new ArrayList<Integer>()));
            HangmanModel.levels.add(13,new Level("14",false,"NEDSÆTTELSE",new ArrayList<Integer>()));
            HangmanModel.levels.add(14,new Level("15",false,"MENNESKESØN",new ArrayList<Integer>()));
            HangmanModel.levels.add(15,new Level("16",false,"ABSOLUTIST",new ArrayList<Integer>()));
        }
    }
}
