package com.example.myapplication.Model;

import java.util.ArrayList;

public class HangmanModel {
      // Hashmap with contains current word, and knows if its geussed or not
    public static  String currentWord;
    public static  ArrayList<Letter> geussWordList = new ArrayList<Letter>();

    // Arraylist of hangman levels
    public static ArrayList<Level> levels = new ArrayList<Level>();

    // Letter user can pick
    public static ArrayList<Letter> alphabet = new ArrayList<Letter>(){
        {
            add(0,new Letter('A',false));
            add(1,new Letter('B',false));
            add(2,new Letter('C',false));
            add(3,new Letter('D',false));
            add(4,new Letter('E',false));
            add(5,new Letter('F',false));
            add(6,new Letter('G',false));
            add(7,new Letter('H',false));
            add(8,new Letter('I',false));
            add(9,new Letter('J',false));
            add(10,new Letter('K',false));
            add(11,new Letter('L',false));
            add(12,new Letter('M',false));
            add(13,new Letter('N',false));
            add(14,new Letter('O',false));
            add(15,new Letter('P',false));
            add(16,new Letter('Q',false));
            add(17,new Letter('R',false));
            add(18,new Letter('S',false));
            add(19,new Letter('T',false));
            add(20,new Letter('U',false));
            add(21,new Letter('V',false));
            add(22,new Letter('W',false));
            add(23,new Letter('X',false));
            add(24,new Letter('Y',false));
            add(25,new Letter('Z',false));
            add(26,new Letter('Æ',false));
            add(27,new Letter('Ø',false));
            add(28,new Letter('Å',false));
        }
    };
    }
