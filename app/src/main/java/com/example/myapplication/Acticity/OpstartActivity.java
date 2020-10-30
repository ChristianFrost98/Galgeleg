package com.example.myapplication.Acticity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class OpstartActivity extends AppCompatActivity implements Runnable {

    static OpstartActivity opstart = null;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Velkomst_akt", "aktiviteten blev startet!");

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.logo);
        setContentView(iv);

        iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.egen_anim));

        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000);
        }
        opstart = this;
    }

    @Override
    public void run() {
        startActivity(new Intent(this, LevelActivity.class));
        // overgang til næste aktivitet
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        opstart.finish();  // <2> Luk velkomstaktiviteten
        opstart = null;
    }

    @Override
    public void finish() {
        super.finish();
        handler.removeCallbacks(this);
    }

}
