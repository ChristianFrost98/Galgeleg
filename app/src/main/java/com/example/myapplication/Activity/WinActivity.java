package com.example.myapplication.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Logic.HangmanLogic;
import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.R;

import java.util.Collections;

public class WinActivity extends AppCompatActivity {
    // Save preferences
    private SharedPreferences prefs;

    // Layout elements
    LinearLayout highScoreList;
    LinearLayout linearLayoutButtons;
    ImageView view_levels;
    ImageView next_level;

    ImageView home_popup;
    ImageView try_again_popup;

    // Ekstra information
    int score = 0;
    int level = 0;
    String word = "";

    // Popup
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // Hent af views
        highScoreList = findViewById(R.id.highScoreList);
        view_levels = findViewById(R.id.view_levels);
        next_level = findViewById(R.id.next_level);
        linearLayoutButtons = findViewById(R.id.linearLayoutButtons);


        // Get ekstra from intent
        score = getIntent().getIntExtra("EXTRA_SCORE",0);
        level = getIntent().getIntExtra("EXTRA_LEVEL",0);
        word = getIntent().getStringExtra("EXTRA_WORD");
        TextView word_won = findViewById(R.id.word_won);
        TextView scoreText = findViewById(R.id.score);

        word_won.setText(word);
        scoreText.setText(""+score);

        // Retreieve highscore and add new score
         HangmanModel.levels.get(level).getHighscores().add(score);

        //Sort highscore
        Collections.sort(HangmanModel.levels.get(level).getHighscores(),Collections.<Integer>reverseOrder());

        // Store new data
        HangmanLogic.storePrefs(this);

        // Set into the Linear view
        for(int i=0;i< HangmanModel.levels.get(level).getHighscores().size();i++) {
            TextView highScoreView = new TextView(this);
            if(i%2 == 0){
                highScoreView.setBackgroundColor(Color.parseColor("#4FBB3E"));
            }
            highScoreView.setText(""+ HangmanModel.levels.get(level).getHighscores().get(i));
            highScoreView.setHeight(80);
            highScoreView.setGravity(Gravity.CENTER);
            highScoreList.addView(highScoreView);
        }

        view_levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_levelsIntent();
            }
        });

        next_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_levelIntent();
            }
        });
    }

    public void view_levelsIntent(){
        Intent intent = new Intent(this, LevelActivity.class);
        intent.putExtra("EXTRA_LEVEL", level);
        startActivity(intent);
    }

    public void next_levelIntent(){
        if(++level == HangmanModel.levels.size()){
            Intent intent = new Intent(this, LevelActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, GeussingActivity.class);
            intent.putExtra("EXTRA_LEVEL", ++level);
            startActivity(intent);
        }
    }


}