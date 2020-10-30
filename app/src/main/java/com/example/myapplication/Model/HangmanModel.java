package com.example.myapplication.Model;

import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HangmanModel {
    // Hashmap with contains current word, and knows if its geussed or not
    public static  String currentWord;
    public static  HashMap<Character,Boolean> currentGuessWord;

    // Make array of possible words
    public static ArrayList<String> possibleWords = new ArrayList<String>() {
        {
            add("BIL");
            add("COMPUTER");
            add("PROGRAMMERING");
            add("MOTORVEJ");
            add("BUSRUTE");
            add("GANGSTI");
            add("SKOVSNEGEL");
            add("SOLSORT");
            add("TYVE");
        }
    };

    // Make array of possible words
    public static  HashMap<Character,Boolean> levelsCleared = new HashMap<Character,Boolean>(){
        {
            put('1',false);
            put('2',false);
            put('3',false);
            put('4',false);
            put('5',false);
            put('6',false);
            put('7',false);
            put('8',false);
            put('9',false);
        }
    };


    // Letter user can pick
    public static  HashMap<Character,Boolean> alphabet = new HashMap<Character,Boolean>(){
        {
            put('A',false);
            put('B',false);
            put('C',false);
            put('D',false);
            put('E',false);
            put('F',false);
            put('G',false);
            put('H',false);
            put('I',false);
            put('J',false);
            put('K',false);
            put('L',false);
            put('M',false);
            put('N',false);
            put('O',false);
            put('P',false);
            put('Q',false);
            put('R',false);
            put('S',false);
            put('T',false);
            put('U',false);
            put('V',false);
            put('W',false);
            put('X',false);
            put('Y',false);
            put('Z',false);
            put('Æ',false);
            put('Ø',false);
            put('Å',false);
        }
    };

    // Status of game
    public static  String status;

    // Current score
    public static  int score;

    // highScores
    public static ArrayList<Integer> highScores;


}
