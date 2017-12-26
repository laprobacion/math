package com.master.math.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.master.math.R;
import com.master.math.activity.addition.AdditionCache;

public class AdditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addition);

        AdditionCache.get().setNumbers("9999","9999");
        AdditionCache.get().setMultiplyNumbers("88","44");

    }
}
