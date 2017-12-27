package com.master.math.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.master.math.R;
import com.master.math.activity.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Util.setAsset(getAssets());
        Button btnCompareFraction = (Button) findViewById(R.id.btnCompareFraction);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf");
        btnCompareFraction.setTypeface(typeface);
        btnCompareFraction.setText("Compare Fractions");
        btnCompareFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FractionActivity.class));
            }
        });

        Button btnLCD = (Button) findViewById(R.id.btnLCD);
        btnLCD.setTypeface(typeface);
        btnLCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LCDMainActivity.class));
            }
        });


    }
}
