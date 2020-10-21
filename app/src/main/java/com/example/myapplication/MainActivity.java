package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
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

        //initializing wordgrid, lettergrid and their corresponding arrayadaapters
        newGame();

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
                    newGameButtons();
                }

                if(wordGuessed(wordList,word)){
                    TextView title = findViewById(R.id.title);
                    title.setText("Du har vundet");
                    wordPointer++;
                    newGame();
                }

                letterAdapter.notifyDataSetChanged();
            }
        });
    }


    public void newGameButtons(){
        for(int i=0;i<letterAdapter.getCount();i++) {
            // Get the color of child
            int color = Color.TRANSPARENT;
            Drawable background = letterGrid.getChildAt(i).findViewById(R.id.letter_button).getBackground();
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();

            // Remove background color of letter_button
            if (color != Color.parseColor("#FFFFFF")) {
                letterGrid.getChildAt(i).setEnabled(false);
                letterGrid.getChildAt(i).setOnClickListener(null);
                letterGrid.getChildAt(i).findViewById(R.id.letter_button).setBackgroundColor(Color.parseColor("#eb4034"));
            }
        }
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