package com.master.math.activity.addition;


import android.app.Activity;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.Util;

public class AdditionProcessor {
    private Activity activity;
    private TextView mulDown, mulUp, mulSymbol, numUp1,numUp2,numUp3,numUp4,
            numDown1,numDown2,numDown3,numDown4,plus,lineAdd,topNum1,
            topNum2,topNum3,topNum4,ans1,ans2,ans3,ans4,winAns1,winAns2;

    public AdditionProcessor(Activity activity){
        this.activity = activity;
        mulDown = Util.getEditTextWithFont(activity, R.id.mulDown);
        mulUp = Util.getEditTextWithFont(activity, R.id.mulUp);
        mulSymbol = Util.getEditTextWithFont(activity, R.id.mulSymbol);
        numUp1 = Util.getEditTextWithFont(activity, R.id.numUp1);
        numUp2 = Util.getEditTextWithFont(activity, R.id.numUp2);
        numUp3 = Util.getEditTextWithFont(activity, R.id.numUp3);
        numUp4 = Util.getEditTextWithFont(activity, R.id.numUp4);
        numDown1 = Util.getEditTextWithFont(activity, R.id.numDown1);
        numDown2 = Util.getEditTextWithFont(activity, R.id.numDown2);
        numDown3 = Util.getEditTextWithFont(activity, R.id.numDown3);
        numDown4 = Util.getEditTextWithFont(activity, R.id.numDown4);
        plus = Util.getEditTextWithFont(activity, R.id.plus);
        lineAdd = Util.getEditTextWithFont(activity, R.id.lineAdd);
        topNum1 = Util.getTextViewWithFontInvisible(activity, R.id.topNum1);
        topNum2 = Util.getTextViewWithFontInvisible(activity, R.id.topNum2);
        topNum3 = Util.getTextViewWithFontInvisible(activity, R.id.topNum3);
        topNum4 = Util.getTextViewWithFontInvisible(activity, R.id.topNum4);
        ans1 = Util.getTextViewWithFontInvisible(activity, R.id.ans1);
        ans2 = Util.getTextViewWithFontInvisible(activity, R.id.ans2);
        ans3 = Util.getTextViewWithFontInvisible(activity, R.id.ans3);
        ans4 = Util.getTextViewWithFontInvisible(activity, R.id.ans4);
        winAns1 = Util.getTextViewWithFontInvisible(activity, R.id.winAns1);
        winAns2 = Util.getTextViewWithFontInvisible(activity, R.id.winAns2);

    }
}
