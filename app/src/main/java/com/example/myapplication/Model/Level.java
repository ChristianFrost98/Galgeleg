package com.example.myapplication.Model;

public class Level  {

    String level;
    boolean cleared;
    String word;

    public Level(String level,boolean cleared,String word)
    {
        this.level=level;
        this.cleared=cleared;
        this.word = word;
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