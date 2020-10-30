package com.example.myapplication.Acticity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class LevelActivity extends AppCompatActivity {
    ArrayAdapter<String> levelAdapter;
    ArrayList<String> levels;
    GridView levelGrid;
    private SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // Instantiate lists
        levels = new ArrayList<>();

        // Hent af views
        levelGrid = findViewById(R.id.levelGrid);

        // Set levels
        int level = 1;
        for(int i=0;i<HangmanModel.possibleWords.size()-1;i++){
            levels.add(""+level);
            level++;
        }
        //data bind GridView with ArrayAdapter
        levelAdapter = new ArrayAdapter<>(this, R.layout.level_button, R.id.levelButton, levels);
        levelGrid.setAdapter(levelAdapter);
        levelGrid.setNumColumns(4);

        // Make a clicklistner on the lettergrid
        levelGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToLoseActivity(position);
            }
        });
    }


    public void goToLoseActivity(int level){
        Intent intent = new Intent(this, GeussingActivity.class);
        intent.putExtra("EXTRA_LEVEL", level);
        startActivity(intent);
    }
}


