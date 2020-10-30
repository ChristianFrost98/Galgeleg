package com.example.myapplication.Model;

import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HangmanModel {
      // Hashmap with contains current word, and knows if its geussed or not
    public static  String currentWord;
    public static  ArrayList<GeussWord> geussWordList = new ArrayList<GeussWord>();


    public static ArrayList<Level> levels = new ArrayList<Level>(){
        {
            add(0,new Level("1",false,"BIL"));
            add(1,new Level("2",false,"COMPUTER"));
            add(2,new Level("3",false,"MOTORVEJ"));
            add(3,new Level("4",false,"BUSRUTE"));
            add(4,new Level("5",false,"GANGSTI"));
            add(5,new Level("6",false,"SKOVSNEGEL"));
            add(6,new Level("7",false,"SOLSORT"));
            add(7,new Level("8",false,"TYVE"));

        }
    };


    // Letter user can pick
    public static ArrayList<Letter> alphabet = new ArrayList<Letter>(){
        {
            add(0,new Letter('A',false));
            add(1,new Letter('B',false));
            add(2,new Letter('C',false));
            add(3,new Letter('D',false));
            add(4,new Letter('E',false));
            add(5,new Letter('F',false));
            add(6,new Letter('H',false));
            add(7,new Letter('I',false));
            add(8,new Letter('J',false));
            add(9,new Letter('K',false));
            add(10,new Letter('L',false));
            add(11,new Letter('M',false));
            add(12,new Letter('N',false));
            add(13,new Letter('O',false));
            add(14,new Letter('P',false));
            add(15,new Letter('Q',false));
            add(16,new Letter('R',false));
            add(17,new Letter('S',false));
            add(18,new Letter('T',false));
            add(19,new Letter('U',false));
            add(20,new Letter('V',false));
            add(21,new Letter('W',false));
            add(22,new Letter('X',false));
            add(23,new Letter('Y',false));
            add(24,new Letter('Z',false));
            add(25,new Letter('Æ',false));
            add(26,new Letter('Ø',false));
            add(27,new Letter('Å',false));
        }
    };

    // Status of game
    public static  String status;

    // Current score
    public static  int score;

    // highScores
    public static ArrayList<Integer> highScores;


}
