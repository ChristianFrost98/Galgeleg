package com.example.myapplication.Model;

import java.util.ArrayList;

public class Level  {

    String level;
    boolean cleared;
    String word;
    ArrayList<Integer> highscores;

    public Level(String level, boolean cleared, String word, ArrayList<Integer> highscores)
    {
        this.level=level;
        this.cleared=cleared;
        this.word = word;
        this.highscores = highscores;
    }

    public ArrayList<Integer> getHighscores() {
        return highscores;
    }

    public void setHighscores(ArrayList<Integer> highscores) {
        this.highscores = highscores;
    }


    public String getLevel() {
        return level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isCleared() {
        return cleared;
    }


    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }


}