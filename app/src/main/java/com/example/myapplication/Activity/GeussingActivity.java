
package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Adapter.GeussWordAdapter;
import com.example.myapplication.Adapter.LetterAdapter;
import com.example.myapplication.Logic.HangmanLogic;
import com.example.myapplication.Model.GeussWord;
import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.Model.Letter;
import com.example.myapplication.R;

import java.util.ArrayList;
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

    //Wrong guesses
    int wrongGuess = 1;
    int wordPointer = 0;

    // wordlists
    ArrayList<String> wordList;
    ArrayList<Letter> letterlist;
    ArrayList<String> buttonList;

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

        // Reset game
        HangmanLogic.resetGame();

        // Instanciate Arraylists for the adapters
        letterlist = new ArrayList<Letter>();
        HangmanModel.geussWordList = new ArrayList<GeussWord>();
        wordList = new ArrayList<String>();

        // Populate wordgrid
        HangmanModel.currentWord = HangmanModel.levels.get(wordPointer).getWord();
        for(int i=0;i<HangmanModel.currentWord.length();i++){
            HangmanModel.geussWordList.add(new GeussWord(HangmanModel.currentWord.charAt(i),false));
        }

        // Set adapter for the gridviews
        final LetterAdapter letterAdapter = new LetterAdapter(this,R.layout.letter_button,HangmanModel.alphabet);
        letterGrid.setAdapter(letterAdapter);
        letterGrid.setNumColumns(GridView.AUTO_FIT);

        // Setup the array apdater
        final GeussWordAdapter GeussWordAdapter = new GeussWordAdapter(this,R.layout.under_score,HangmanModel.geussWordList);
        wordGrid.setAdapter(GeussWordAdapter);
        wordGrid.setNumColumns(GridView.AUTO_FIT);

        // Timebefore
        timeBefore = new Date().getTime();


        // Setup listner for letter geuses
        letterGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove letter from lettergrid
                HangmanModel.alphabet.get(position).setGeussed(true);
                letterAdapter.notifyDataSetChanged();


                String guessedLetter = HangmanModel.alphabet.get(position).getLetter().toString();
                if(HangmanLogic.wordContainLetter(guessedLetter)){
                    GeussWordAdapter.notifyDataSetChanged();
                } else {
                    setGalgeImage();
                }

                if(HangmanLogic.isWordGuessed()){
                    HangmanModel.levels.get(wordPointer).setCleared(true);
                    goToWinActivity();
                }

                if(wrongGuess == 6){
                    goToLoseActivity();
                }

            }
        });


    }

    public void gotoLevels(){
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }


    public void goToLoseActivity(){
        Intent intent = new Intent(this, LoseActivity.class);
        intent.putExtra("EXTRA_WORD", HangmanModel.currentWord);
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
