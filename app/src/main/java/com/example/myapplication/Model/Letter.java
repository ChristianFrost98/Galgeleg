package com.example.myapplication.Model;

public class Letter {

    Character letter;
    boolean geussed;

    public Letter(Character letter,boolean geussed)
    {
        this.letter=letter;
        this.geussed=geussed;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public boolean isGeussed() {
        return geussed;
    }

    public void setGeussed(boolean geussed) {
        this.geussed = geussed;
    }
}
