package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Letter;
import com.example.myapplication.Model.Level;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LetterAdapter extends ArrayAdapter {

    ArrayList<Letter> letterList = new ArrayList<>();

    public LetterAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        letterList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.letter_button, null);
        TextView textView = (TextView) v.findViewById(R.id.letter_button);
        textView.setText(letterList.get(position).getLetter().toString());
        if(letterList.get(position).isGeussed()){
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return v;

    }

}