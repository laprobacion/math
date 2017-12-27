package com.master.math.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.Util;

import java.util.HashMap;
import java.util.Map;

public class LCDActivity extends AppCompatActivity {

    public static final String NUM_1 = "NUM1";
    public static final String NUM_2 = "NUM2";
    public static final String NUM_3 = "NUM3";
    public static final String LCD = "LCD";
    private int mul1 = 2;
    private int mulTop1 = 150;
    private int mul2 = 2;
    private int mulTop2 = 150;
    private int mul3 = 2;
    private int mulTop3 = 150;
    private TextView denom1,denom2,denom3,line1,line2,line3,lcd;
    private Map<Integer, TextView> denomMul1;
    private RelativeLayout lcdLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lcd);
        lcdLayout = (RelativeLayout) findViewById(R.id.lcdLayout);
        denom1 = Util.getTextViewWithFont(this, R.id.denom1);
        denom2 = Util.getTextViewWithFont(this, R.id.denom2);
        denom3 = Util.getTextViewWithFont(this, R.id.denom3);
        lcd = Util.getTextViewWithFont(this, R.id.lcd);
        line1 = Util.getTextViewWithFont(this, R.id.line1);
        line2 = Util.getTextViewWithFont(this, R.id.line2);
        line3 = Util.getTextViewWithFont(this, R.id.line3);
        Util.showWithText(denom1,getIntent().getStringExtra(NUM_1));
        Util.showWithText(denom2,getIntent().getStringExtra(NUM_2));
        Util.showWithText(denom3,getIntent().getStringExtra(NUM_3));
        Util.showWithText(lcd,getIntent().getStringExtra(LCD));
        setDenom1OnClick();
        setDenom2OnClick();
        setDenom3OnClick();
        denomMul1 = new HashMap<Integer, TextView>();
    }

    private void setDenom1OnClick(){
        denom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = Util.getTextViewWithFont(new TextView(LCDActivity.this));
                tv.setId(10+mul1);
                tv.setTextSize(40);
                int num = Integer.valueOf(denom1.getText().toString()) * mul1;
                Util.showWithText(tv,String.valueOf(num));

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(250,mulTop1+=100,0,0);

                mul1++;

                tv.setLayoutParams(lp);
                lcdLayout.addView(tv);
                denomMul1.put(tv.getId(), tv);
            }
        });
    }

    private void setDenom2OnClick(){
        denom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = Util.getTextViewWithFont(new TextView(LCDActivity.this));
                tv.setId(10+mul2);
                tv.setTextSize(40);
                int num = Integer.valueOf(denom2.getText().toString()) * mul2;
                Util.showWithText(tv,String.valueOf(num));

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(470,mulTop2+=100,0,0);

                mul2++;

                tv.setLayoutParams(lp);
                lcdLayout.addView(tv);
            }
        });
    }

    private void setDenom3OnClick(){
        denom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = Util.getTextViewWithFont(new TextView(LCDActivity.this));
                tv.setId(10+mul3);
                tv.setTextSize(40);
                int num = Integer.valueOf(denom3.getText().toString()) * mul3;
                Util.showWithText(tv,String.valueOf(num));

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(740,mulTop3+=100,0,0);

                mul3++;

                tv.setLayoutParams(lp);
                lcdLayout.addView(tv);
            }
        });
    }
}
