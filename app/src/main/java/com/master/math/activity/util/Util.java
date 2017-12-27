package com.master.math.activity.util;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import com.master.math.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Util {
    public static final String DOUBLE_SPACES = "  ";
    private static Util _this;
    private static AssetManager instanceAsset;
    private static TranslateAnimation animate;
    private Util(){}
    public static Util getInstance(){
        if(_this == null){
            _this = new Util();
            TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
            shake.setDuration(500);
            shake.setInterpolator(new CycleInterpolator(7));
            _this.animate = shake;
        }
        return _this;
    }

    public static String generateRandomNumbers(boolean includeZero){
        Random rand = new Random();
        Integer  n = 0;
        if(!includeZero){
            n = rand.nextInt(9) + 1;
        }else{
            n = rand.nextInt(9);
        }
        return n.toString();
    }
    public static String generate2DigsRandomNumbers(int max){
        Random rand = new Random();
        Integer  n = 0;
        n = rand.nextInt(max) + 1;
        return n.toString();
    }
    private static String generateCompositeNumbers(){
        int num = Integer.valueOf(generate2DigsRandomNumbers(20));
        while(true) {
            int count = 0;
            for (int i = 1; i <= num; i++) {
                if ((num % i) == 0) {
                    count++;
                }
            }
            if(count > 2){
                break;
            }else{
                num = Integer.valueOf(generate2DigsRandomNumbers(20));
            }
        }
        return String.valueOf(num);
    }
    private static List<String> generateDenominators(){
        List<String> denoms = new ArrayList<String>();
        denoms.add(generateCompositeNumbers());
        denoms.add(generateCompositeNumbers());
        denoms.add(generateCompositeNumbers());
        Collections.sort(denoms);
        return denoms;
    }
    public static String[][] generateProperFraction(){
        List<String> denoms = generateDenominators();
        String [][] fractions = new String [3][2];
        int i = 0;
        for(String s : denoms){
            while(true){
                int j = 0;
                String numerator = generate2DigsRandomNumbers(10);
                if(Integer.valueOf(numerator) > Integer.valueOf(s) ){
                    numerator = generate2DigsRandomNumbers(10);
                }else{
                    fractions[i][j] = numerator;
                    fractions[i][j+1] = s;
                    break;
                }
            }
            i++;
        }
        return fractions;
    }
    public static String[] generateProperFractions(){
        String numerator = generate2DigsRandomNumbers(50);
        String denominator = generate2DigsRandomNumbers(70);
        while(true){
            if(Integer.valueOf(numerator) > Integer.valueOf(denominator)){
                denominator = generate2DigsRandomNumbers(75);
            }else{
                break;
            }
        }
        return new String[]{numerator,denominator};
    }
    public static void setAsset(AssetManager asset){
        instanceAsset = asset;
    }
    public static EditText getEditTextWithFont(Activity activity,int id){
        EditText et = (EditText) activity.findViewById(id);
        et.setTypeface(Typeface.createFromAsset(instanceAsset,"fonts/EraserDust.ttf"));
        return et;
    }
    public static TextView getTextViewWithFont(Activity activity,int id){
        TextView tv =(TextView) activity.findViewById(id);
        tv.setTypeface(Typeface.createFromAsset(instanceAsset,"fonts/EraserDust.ttf"));
        tv.setTextColor(Color.argb(255, 255, 255, 255));
        return tv;
    }
    public static TextView getTextViewWithFont(TextView tv){
        tv.setTypeface(Typeface.createFromAsset(instanceAsset,"fonts/EraserDust.ttf"));
        tv.setTextColor(Color.argb(255, 255, 255, 255));
        return tv;
    }
    public static TextView getTextViewWithFontInvisible(Activity activity,int id){
        TextView tv = getTextViewWithFont(activity, id);
        tv.setVisibility(View.INVISIBLE);
        return tv;
    }
    public static void showWithText(TextView tv, String txt){
        tv.setText(txt == null ? tv.getText().toString() : txt);
        tv.setTextColor(Color.argb(255, 255, 255, 255));
        tv.setVisibility(View.VISIBLE);
        tv.setBackground(null);
        tv.invalidate();
    }
    public static void showWithBG(TextView tv, Activity activity){
        showWithText(tv,DOUBLE_SPACES);
        tv.setBackground(activity.getResources().getDrawable(R.drawable.border));
        tv.invalidate();
    }
    public static void removeListeners(TextView tv){
        tv.setOnClickListener(null);
        tv.setOnTouchListener(null);
        tv.setOnDragListener(null);
    }
    public static void hide(TextView tv){
        tv.setVisibility(View.INVISIBLE);
        tv.invalidate();
    }
    public static void hide(TextView... tvs){
        for(TextView tv: tvs){
            hide(tv);
        }
    }
    public static void show(TextView... tvs){
        for(TextView tv: tvs){
            showWithText(tv,null);
        }
    }
    public static void blur(TextView... tvs){
        for(TextView tv: tvs){
            if(tv.getVisibility() == View.VISIBLE) {
                float radius = tv.getTextSize() / 10;
                BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.INNER);
                tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                tv.getPaint().setMaskFilter(filter);
            }
        }
    }
    public static void removeBlur(TextView... tvs){
        for(TextView tv: tvs){
            if(tv.getVisibility() == View.VISIBLE) {
                float radius = tv.getTextSize() / 10;
                BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.INNER);
                tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                tv.getPaint().setMaskFilter(null);
            }
        }
    }
    public static TranslateAnimation shakeError() {
        return Util.getInstance().animate;
    }
}
