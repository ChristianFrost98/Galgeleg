package com.example.myapplication.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.myapplication.Logic.HangmanLogic;
import com.example.myapplication.Model.HangmanModel;
import com.example.myapplication.Adapter.LevelAdapter;
import com.example.myapplication.R;


public class LevelActivity extends AppCompatActivity {
    GridView levelGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // Retrieve store levels data
        if(HangmanModel.levels.size() == 0){
            HangmanLogic.retrievePrefs(this);
        } else {
            HangmanLogic.storePrefs(this);
        }

        // Iniciate view
        levelGrid = findViewById(R.id.levelGrid);

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


