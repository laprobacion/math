package com.master.math.activity.lcd;


import android.app.Activity;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.Util;

import java.util.ArrayList;
import java.util.List;

public class LCDProcessor {

    private Activity activity;

    private TextView num1,line1,denom1,num2,line2,denom2,num3,line3,denom3;
    private List<Integer> denomMul1 = new ArrayList<Integer>();
    private List<Integer> denomMul2 = new ArrayList<Integer>();
    private List<Integer> denomMul3 = new ArrayList<Integer>();
    private String lcd;
    public LCDProcessor(Activity activity){
        this.activity = activity;
        num1 = Util.getTextViewWithFont(activity, R.id.num1);
        line1 = Util.getTextViewWithFont(activity, R.id.line1);
        denom1 = Util.getTextViewWithFont(activity, R.id.denom1);

        num2 = Util.getTextViewWithFont(activity, R.id.num2);
        line2 = Util.getTextViewWithFont(activity, R.id.line2);
        denom2 = Util.getTextViewWithFont(activity, R.id.denom2);

        num3 = Util.getTextViewWithFont(activity, R.id.num3);
        line3 = Util.getTextViewWithFont(activity, R.id.line3);
        denom3 = Util.getTextViewWithFont(activity, R.id.denom3);
        setFractions();
        lcd = String.valueOf(findLCD());
    }

    public String getLcd() {        return lcd;    }

    private void setFractions(){
        String [][] fractions = Util.generateProperFraction();
        Util.showWithText(num1,fractions[0][0]);
        Util.showWithText(denom1,fractions[0][1]);
        Util.showWithText(num2,fractions[1][0]);
        Util.showWithText(denom2,fractions[1][1]);
        Util.showWithText(num3,fractions[2][0]);
        Util.showWithText(denom3,fractions[2][1]);
        generatePossible(Integer.valueOf(denom1.getText().toString()),denomMul1);
        generatePossible(Integer.valueOf(denom2.getText().toString()),denomMul2);
        generatePossible(Integer.valueOf(denom3.getText().toString()),denomMul3);
    }
    private void generatePossible(int num,List<Integer> denomMul){
        for(int i=2; i<=40; i++){
            denomMul.add(i * num);
        }
    }
    private int findLCD(){
        int lcd = 0;
        denom1: for(int i1 : denomMul1){
            denom2:for(int i2 : denomMul2){
                if(i1 == i2){
                    denom3:for(int i3 : denomMul3){
                        if(i2 == i3){
                            lcd = i3;
                            break denom1;
                        }
                    }
                }
            }
        }
        return lcd;
    }
}
