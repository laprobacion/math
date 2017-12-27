package com.master.math.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.master.math.R;

import fraction.CompareFractionProcessor;
import fraction.CompareFractionStep;

public class FractionActivity extends AppCompatActivity {
    CompareFractionProcessor processor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fraction);
        this.processor = new CompareFractionProcessor(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(this.processor != null && this.processor.isReady()){
            if(this.processor.getStep() == CompareFractionStep.STEP_1 || this.processor.getStep() == CompareFractionStep.STEP_2){
                this.processor.execute();
            }
        }
    }
}
