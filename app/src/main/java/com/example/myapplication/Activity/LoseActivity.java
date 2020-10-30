package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);


        // Set word
        String word = getIntent().getStringExtra("EXTRA_WORD");
        TextView textView = findViewById(R.id.loseWord);
        textView.setText(word);



    }



}