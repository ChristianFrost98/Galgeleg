package com.example.myapplication.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.Logic.HangmanLogic;
import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.Adapter.LevelAdapter;
import com.example.myapplication.R;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LevelActivity extends AppCompatActivity {
    GridView levelGrid;
    LottieAnimationView loading;
    TextView retrieveText;
    Button resetButton;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());  // håndtag til forgrundstråden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        levelGrid = findViewById(R.id.levelGrid);

        // Retrieve store levels data
        if(HangmanModel.levels.size() == 0){
            retrieveLevels();
        } else {
            makeGrid();
        }

        // Retrieves new list of words
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HangmanModel.justResat = true;
                retrieveLevels();
            }
        });
    }

    public void retrieveLevels(){
        retrieveText = findViewById(R.id.retrieveText);
        loading = findViewById(R.id.loading);
        retrieveText.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        levelGrid.setVisibility(View.GONE);

        bgThread.execute(() -> {
            try {
                HangmanLogic.retrievePrefs(this);
                HangmanLogic.storePrefs(this);

                // Post retrieved info
                /* The delay makes it possible to actually see the animation
                    Otherwise I could just post() the uiThread, but it looks better with a delay */
                uiThread.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(View.GONE);
                        retrieveText.setVisibility(View.GONE);
                        makeGrid();
                    }
                }, 2000);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void makeGrid(){
        // Make view visisble
        levelGrid.setVisibility(View.VISIBLE);

        // Iniciate view
        LevelAdapter myAdapter=new LevelAdapter(this,R.layout.level_button, HangmanModel.levels);
        levelGrid.setNumColumns(4);
        levelGrid.setAdapter(myAdapter);

        levelGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotToLevel(position);
            }
        });
    }


    public void gotToLevel(int level){
        Intent intent = new Intent(this, GeussingActivity.class);
        intent.putExtra("EXTRA_LEVEL", level);
        startActivity(intent);
    }
}


