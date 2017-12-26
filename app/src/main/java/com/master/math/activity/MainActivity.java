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

        Button btnMultiply = (Button) findViewById(R.id.btnMultiply);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf");
        btnMultiply.setTypeface(typeface);
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiplyActivity.class));
            }
        });

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setTypeface(typeface);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdditionActivity.class));
            }
        });

        Util.setAsset(getAssets());

    }
}
