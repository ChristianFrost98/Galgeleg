package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.Model.Letter;
import com.example.myapplication.R;

import java.util.ArrayList;

public class GeussWordAdapter extends ArrayAdapter {

    ArrayList<Letter> geussWords = new ArrayList<>();

    public GeussWordAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        geussWords = objects;
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
        v = inflater.inflate(R.layout.under_score, null);
        TextView textView = (TextView) v.findViewById(R.id.letterBox);
        if(geussWords.get(position).isGeussed()){
            String newLetter = geussWords.get(position).getLetter().toString();
            textView.setText(newLetter);
        } else {
            textView.setText("");
        }
        return v;

    }

}