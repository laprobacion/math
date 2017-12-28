package com.master.math.activity;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.master.math.R;
import com.master.math.activity.lcd.LCDCache;
import com.master.math.activity.util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.master.math.activity.util.Util.shakeError;

public class LCDActivity extends AppCompatActivity {

    public static final String NUM_1 = "NUM1";
    public static final String NUM_2 = "NUM2";
    public static final String NUM_3 = "NUM3";
    public static final String LCD = "LCD";
    private int mul1 = 1;
    private int mulTop1 = 150;
    private int mul2 = 1;
    private int mulTop2 = 150;
    private int mul3 = 1;
    private int mulTop3 = 150;
    private TextView selected1;
    private TextView selected2;
    private TextView selected3;
    private TextView denom1,denom2,denom3,line1,line2,line3,lcd;
    private Map<Integer, TextView> denomMul1;
    private RelativeLayout lcdLayout;
    private int intLCD;
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
        setDenom1OnClick();
        setDenom2OnClick();
        setDenom3OnClick();
        denomMul1 = new HashMap<Integer, TextView>();
        intLCD = Integer.valueOf(getIntent().getStringExtra(LCD));
        isTooLarge();
    }

    private void isTooLarge(){
        int d1 = Integer.valueOf(denom1.getText().toString());
        int d2 = Integer.valueOf(denom2.getText().toString());
        int d3 = Integer.valueOf(denom3.getText().toString());
        int[] ints = {d1, d2, d3};
        Arrays.sort(ints);
        if((intLCD / ints[0]) >= 15){
            Util.showWithText(lcd,"warning too large.");
        }else{
            Util.hide(lcd);
        }
    }
    private void createOnClickListener(final TextView tv){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup.LayoutParams lp = tv.getLayoutParams();
                int left = tv.getLeft();
                if(left == 250){
                    if(selected1 != null){
                        selected1.setTextColor(Color.argb(255, 255, 255, 255));
                    }
                    selected1 = tv;
                    tv.setTextColor(Color.argb(255, 255, 255, 0));
                }else if(left == 470){
                    if(selected2 != null){
                        selected2.setTextColor(Color.argb(255, 255, 255, 255));
                    }
                    selected2 = tv;
                    tv.setTextColor(Color.argb(255, 255, 255, 0));
                }else if(left == 740){
                    if(selected3 != null ){
                        selected3.setTextColor(Color.argb(255, 255, 255, 255));
                    }
                    selected3 = tv;
                    tv.setTextColor(Color.argb(255, 255, 255, 0));
                }
                if(selected1 != null && selected2 != null && selected3 != null){
                    if(Integer.valueOf(selected1.getText().toString()) == intLCD &&
                            Integer.valueOf(selected2.getText().toString()) == intLCD
                            && Integer.valueOf(selected3.getText().toString()) == intLCD){
                        LCDCache.get().setFinished(true);
                        finish();
                    }else{
                        selected1.startAnimation(shakeError());
                        selected2.startAnimation(shakeError());
                        selected3.startAnimation(shakeError());
                        selected1.setTextColor(Color.argb(255, 255, 255, 255));
                        selected2.setTextColor(Color.argb(255, 255, 255, 255));
                        selected3.setTextColor(Color.argb(255, 255, 255, 255));
                        selected1 = null;
                        selected2 = null;
                        selected3 = null;
                        Toast.makeText(LCDActivity.this," Invalid LCD ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
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
                createOnClickListener(tv);
            }
        });
    }

    private void setDenom2OnClick(){
        denom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = Util.getTextViewWithFont(new TextView(LCDActivity.this));
                tv.setId(40+mul2);
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
                createOnClickListener(tv);
            }
        });
    }

    private void setDenom3OnClick(){
        denom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = Util.getTextViewWithFont(new TextView(LCDActivity.this));
                tv.setId(80+mul3);
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
                createOnClickListener(tv);
            }
        });
    }
}
