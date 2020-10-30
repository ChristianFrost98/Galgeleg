package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Level;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LevelAdapter extends ArrayAdapter {

    ArrayList<Level> levelList = new ArrayList<>();

    public LevelAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        levelList = objects;
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
        v = inflater.inflate(R.layout.level_button, null);
        TextView textView = (TextView) v.findViewById(R.id.levelButton);
        ImageView imageView = (ImageView) v.findViewById(R.id.checkmark);
        textView.setText(levelList.get(position).getLevel());
        if(levelList.get(position).isCleared()){
            imageView.setVisibility(View.VISIBLE);
            imageView.bringToFront();
        }
        return v;

    }

}