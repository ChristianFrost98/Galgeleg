package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Opstart extends AppCompatActivity implements Runnable {

    static Opstart opstart = null;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Velkomst_akt", "aktiviteten blev startet!");

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.logo);
        setContentView(iv);

        iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.egen_anim));
        // En lettere måde at lave små animationer på:
        //iv.animate().rotation(360*2).setDuration(2000);

        // Hvis savedInstanceState ikke er null er det en aktivitet der er ved at blive genstartet
        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000); // <1> Kør run() om 3 sekunder
        }
        opstart = this;
    }

    @Override
    public void run() {
        startActivity(new Intent(this, MainActivity.class));
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
