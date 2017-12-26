package com.master.math.activity.util;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.master.math.R;


import java.util.Random;

public class Util {
    public static final String DOUBLE_SPACES = "  ";
    private static Util _this;
    private static AssetManager instanceAsset;
    private Util(){}
    public Util getInstance(){
        if(_this == null){
            _this = new Util();
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
    public static TextView getTextViewWithFontInvisible(Activity activity,int id){
        TextView tv = getTextViewWithFont(activity, id);
        tv.setVisibility(View.INVISIBLE);
        return tv;
    }
    public static void showWithText(TextView tv, String txt){
        tv.setText(txt);
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
}
