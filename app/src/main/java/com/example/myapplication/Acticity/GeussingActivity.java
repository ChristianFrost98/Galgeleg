package com.example.myapplication.Acticity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Logic.HangmanLogic;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GeussingActivity extends AppCompatActivity implements OnClickListener {
    ImageView galge;
    GridView letterGrid;
    GridView wordGrid;
    TextView bokstav;
    String[] guessWord;
    String word;
    String[] bokstaver;
    ArrayAdapter<String> wordAdapter;
    ArrayAdapter<String> letterAdapter;

    //Wrong guesses
    int wrongGuess = 1;
    int wordPointer = 0;

    // wordlists
    List<String> wordList;
    List<String> letterlist;
    List<String> buttonList;

    // Possible words
    List<String> possibleWords;

    // Set Time before an after
    long timeBefore = 0;
    long timeAfter = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fedeste_akt);
        // Hent af views
        galge = findViewById(R.id.galge);
        letterGrid = findViewById(R.id.letterGrid);
        wordGrid = findViewById(R.id.wordGrid);
        bokstav = findViewById(R.id.bokstav);

        // Get information from intent
        wordPointer = getIntent().getIntExtra("EXTRA_LEVEL",0);

        //initializing wordgrid, lettergrid and their corresponding arrayadaapters

        newGame();

        // Set Time before
        timeBefore = new Date().getTime();

        // Make a clicklistner on the lettergrid
        letterGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("BenytEnKnap", "Knap trykket");
                System.out.println("Der blev klikket");
                bokstav.setText("Der blev klikket på " +  bokstaver[position]);

                // Check if guessed letter is in word
                if (wordContainLetter(wordList, bokstaver[position], word) > 0) {
                        wordAdapter.notifyDataSetChanged();
                } else {
                    setGalgeImage();
                }
                // Remove background color of letter_button
                letterGrid.getChildAt(position).setEnabled(false);
                letterGrid.getChildAt(position).setOnClickListener(null);
                letterGrid.getChildAt(position).findViewById(R.id.letter_button).setBackgroundColor(Color.parseColor("#FFFFFF"));

                if(wrongGuess > 6){
                    TextView title = findViewById(R.id.title);
                    title.setText("Du har tabt");
                    goToLoseActivity();
                }

                if(wordGuessed(wordList,word)){
                    goToWinActivity();
                }

                letterAdapter.notifyDataSetChanged();
            }
        });
    }



    public void goToLoseActivity(){
        Intent intent = new Intent(this, LoseActivity.class);
        intent.putExtra("EXTRA_WORD", word);
        startActivity(intent);
    }

    public void goToWinActivity(){
        // Set Time After
        timeAfter = new Date().getTime();
        // Calculate score
        int score = (int) 100000 - ((int)timeAfter - (int)timeBefore);
        if(score < 0){
            score = 0;
        }

        Intent intent = new Intent(this, WinActivity.class);
        intent.putExtra("EXTRA_SCORE", score);
        intent.putExtra("EXTRA_LEVEL", wordPointer);
        intent.putExtra("EXTRA_WORD", word);
        startActivity(intent);
    }




    public boolean wordGuessed(List<String> wordList, String word){
        StringBuilder guessedword = new StringBuilder();
        for(int i=0;i<wordList.size();i++){
            guessedword.append(wordList.get(i));
        }

        return guessedword.toString().equals(word);
    }


    public int wordContainLetter(List<String> wordList,String letter,String word){
        int exist = 0;
        for(int i=0;i<word.length();i++){
            if(letter.charAt(0) == word.charAt(i)){
                wordList.set(i,letter);
                exist++;
            }
        }
        return exist;
    }

    public void newGame(){
        Log.d("new_game","New game kaldes");

        // Make array of possible words
        possibleWords = new ArrayList<String>();
        possibleWords.add("BIL");
        possibleWords.add("COMPUTER");
        possibleWords.add("PROGRAMMERING");
        possibleWords.add("MOTORVEJ");
        possibleWords.add("BUSRUTE");
        possibleWords.add("GANGSTI");
        possibleWords.add("SKOVSNEGEL");
        possibleWords.add("SOLSORT");
        possibleWords.add("TYVE");

        // Instantiate lists
        wordList = new ArrayList<>();
        letterlist = new ArrayList<>();
        buttonList = new ArrayList<>();

        /* Wordgrid */
        word = possibleWords.get(wordPointer);

        // Make empty in the size of the word ("_")
        for (int i=0;i<word.length();i++){
            wordList.add(" ");
       }
        // Setup the array apdater
        wordAdapter = new ArrayAdapter<>(this, R.layout.under_score, R.id.letterBox, wordList);
        wordGrid.setAdapter(wordAdapter);
        wordGrid.setNumColumns(GridView.AUTO_FIT);

        /* Lettergrid */
        bokstaver = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Æ", "Ø", "Å"};
        letterlist = new ArrayList<String>(Arrays.asList(bokstaver));
        //data bind GridView with ArrayAdapter
        letterAdapter = new ArrayAdapter<>(this, R.layout.letter_button, R.id.letter_button, letterlist);
        letterGrid.setAdapter(letterAdapter);
        letterGrid.setNumColumns(GridView.AUTO_FIT);
    }


    public void setGalgeImage(){
        switch(wrongGuess) {
            case 1:
                galge.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                galge.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                galge.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                galge.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                galge.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                galge.setImageResource(R.drawable.forkert6);
                break;
            default:
                break;
        }
        wrongGuess++;
    }


    @Override
    public void onClick(View view) {

    }
}