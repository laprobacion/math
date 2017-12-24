package com.master.math.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.master.math.R;
import multiply.MultiplyCache;
import multiply.MultiplyProcessor;

public class MultiplyActivity extends AppCompatActivity  {

    MultiplyProcessor processor;
    RelativeLayout parentMultiply;
    TextView replay, newProblem;
    EditText userAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_multiply);
        setReplay();
        setNewProblem();
        userAns = (EditText) findViewById(R.id.userAns);
        parentMultiply = (RelativeLayout) findViewById(R.id.parentMultiply);
        if(MultiplyCache.getInstance().getNums() != null){
            processor = new MultiplyProcessor(this,getAssets(),MultiplyCache.getInstance().getNums());
        }else{
            processor = new MultiplyProcessor(this,getAssets());
        }

        parentMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processor.isPopupMultiplyVisible()) {
                    if(validateInput()) {
                        processor.renderPopupWindow(false);
                        processor.renderAnswerWindow(true);
                    }else{
                        Toast.makeText(MultiplyActivity.this, " Input Error ", Toast.LENGTH_SHORT).show();
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
    private void setReplay(){
        replay = (TextView) findViewById(R.id.replay);
        replay.setTextColor(Color.argb(255, 255, 255, 255));
        replay.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiplyCache.getInstance().setNums(processor.getNum1(),processor.getNum2(),processor.getNum3());
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void setNewProblem(){
        newProblem = (TextView) findViewById(R.id.newProblem);
        newProblem.setTextColor(Color.argb(255, 255, 255, 255));
        newProblem.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        newProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiplyCache.getInstance().setNums(null);
                finish();
                startActivity(getIntent());
            }
        });
    }
}
