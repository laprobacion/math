package com.master.math.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.master.math.R;
import com.master.math.activity.util.Util;

import multiply.MultiplyCache;
import multiply.MultiplyProcessor;

public class MultiplyActivity extends AppCompatActivity  {

    MultiplyProcessor processor;
    RelativeLayout parentMultiply;
    TextView formulaPop;
    EditText userAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_multiply);
        userAns = (EditText) findViewById(R.id.userAns);
        parentMultiply = (RelativeLayout) findViewById(R.id.parentMultiply);
        formulaPop = Util.getTextViewWithFont(this,R.id.formulaPop);
        //MultiplyCache.getInstance().setNums(8,7,1,1);
        if(MultiplyCache.getInstance().getNums() != null){
            processor = new MultiplyProcessor(this,getAssets(),MultiplyCache.getInstance().getNums());
        }else{
            processor = new MultiplyProcessor(this,getAssets());
        }

        parentMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processor.isPopupMultiplyVisible()) {
                    if(validateInput() && processor.isFormulaPopAnsCorrect()) {
                        processor.renderPopupWindow(null,false);
                        processor.renderAnswerWindow(true);
                    }else{
                        userAns.startAnimation(shakeError());
                    }
                }
            }
        });

    }
    private boolean validateInput(){
        try{
            if(userAns.getText().toString().trim().equals("")) {
               return false;
            }
            int i = 1 + Integer.valueOf(userAns.getText().toString());
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}
