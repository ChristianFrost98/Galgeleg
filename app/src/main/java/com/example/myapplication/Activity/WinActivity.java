package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;

public class WinActivity extends AppCompatActivity {
    // Save preferences
    private SharedPreferences prefs;

    // Layout elements
    LinearLayout highScoreList;
    ImageView view_levels;
    ImageView next_level;

    // Ekstra information
    int score = 0;
    int level = 0;
    String word = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // Hent af views
        highScoreList = findViewById(R.id.highScoreList);
        view_levels = findViewById(R.id.view_levels);
        next_level = findViewById(R.id.next_level);

        // Get ekstra from intent
        score = getIntent().getIntExtra("EXTRA_SCORE",0);
        level = getIntent().getIntExtra("EXTRA_LEVEL",0);
        word = getIntent().getStringExtra("EXTRA_WORD");
        TextView word_won = findViewById(R.id.word_won);
        TextView scoreText = findViewById(R.id.score);

        word_won.setText(word);
        scoreText.setText(""+score);

        // Retreieve highscore
        ArrayList<Integer> highScores = new ArrayList<>();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int triesAtLevel = prefs.getInt("level" + level,0);
        for(int i = 1;i<triesAtLevel+1;i++){
            int storedScore = prefs.getInt("level"+ level + "_" + i, -1);
            highScores.add(storedScore);
        }
        // Add new score
        highScores.add(score);
        int newScorePointer = triesAtLevel+1;
        prefs.edit().putInt("level"+ level + "_" + newScorePointer,score).apply();
        prefs.edit().putInt("level" + level,newScorePointer).apply();

        //Sort highscore
        Collections.sort(highScores,Collections.<Integer>reverseOrder());

        // Set into the Linear view
        for(int i=0;i<highScores.size();i++) {
            TextView highScoreView = new TextView(this);
            if(i%2 == 0){
                highScoreView.setBackgroundColor(Color.parseColor("#4FBB3E"));
            }
            highScoreView.setText(""+highScores.get(i));
            highScoreView.setHeight(80);
            highScoreView.setGravity(Gravity.CENTER);
            highScoreList.addView(highScoreView);
        }
    }


    public void onClick(View view) {
        if(view == next_level){
            if(++level == HangmanModel.levels.size()){
                Intent intent = new Intent(this, LevelActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, GeussingActivity.class);
                intent.putExtra("EXTRA_LEVEL", ++level);
                startActivity(intent);
            }
        } else if(view == view_levels){
            Intent intent = new Intent(this, LevelActivity.class);
            intent.putExtra("EXTRA_LEVEL", level);
            startActivity(intent);
        }
    }


}