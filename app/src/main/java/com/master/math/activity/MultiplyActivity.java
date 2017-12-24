package com.master.math.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.master.math.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import multiply.DraggedItem;
import multiply.MultiplyProcessor;

public class MultiplyActivity extends AppCompatActivity  {

    MultiplyProcessor processor;
    RelativeLayout parentMultiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_multiply);
        parentMultiply = (RelativeLayout) findViewById(R.id.parentMultiply);
        processor = new MultiplyProcessor(this,getAssets());
        parentMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processor.isPopupMultiplyVisible()) {
                    processor.renderPopupWindow(false);
                    processor.renderAnswerWindow(true);
                }
            }
        });

    }


}
