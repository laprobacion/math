package com.master.math.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.Util;

import multiply.MultiplyCache;

import static com.master.math.activity.util.Util.shakeError;

public class FormActivity extends AppCompatActivity {

    ConstraintLayout form;
    TextView formula,open;
    EditText userAns;
    private int num1;
    private int num2;
    public static final String NUM_1 = "NUM1";
    public static final String NUM_2 = "NUM2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_form);
        form = (ConstraintLayout) findViewById(R.id.parentForm);
        formula = Util.getTextViewWithFont(this,R.id.formula);
        open = Util.getTextViewWithFontInvisible(this,R.id.open);
        userAns = Util.getEditTextWithFont(this,R.id.userAns);
        Intent intent = getIntent();
        num1 = Integer.valueOf(intent.getStringExtra(NUM_1));
        num2 = Integer.valueOf(intent.getStringExtra(NUM_2));
        Util.showWithText(formula, num1 + " x " + num2 + "  = ");
        setFormClick();
        if(intent.getStringExtra(NUM_1).length() == 2 || intent.getStringExtra(NUM_2).length() == 2){
            Util.showWithText(open,"Open Scratch.");
            open.setTextSize(30);
            setOpenOnClick();
        }
    }

    private void setOpenOnClick(){
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i1 = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                boolean isUpperFilled = false;
                if(String.valueOf(num1).length() == 2){
                    i1 = Integer.valueOf(String.valueOf(String.valueOf(num1).charAt(0)));
                    i2 = Integer.valueOf(String.valueOf(String.valueOf(num1).charAt(1)));
                    isUpperFilled = true;
                }
                if(isUpperFilled){
                    i3 = Integer.valueOf(String.valueOf(String.valueOf(num2).charAt(0)));
                    if(String.valueOf(num2).length() == 2){
                        i4 = Integer.valueOf(String.valueOf(String.valueOf(num2).charAt(1)));
                    }
                }else{
                    i1 = Integer.valueOf(String.valueOf(String.valueOf(num2).charAt(0)));
                    i2 = Integer.valueOf(String.valueOf(String.valueOf(num2).charAt(1)));
                    i3 = Integer.valueOf(String.valueOf(String.valueOf(num1).charAt(0)));
                    if(String.valueOf(num1).length() == 2){
                        i4 = Integer.valueOf(String.valueOf(String.valueOf(num1).charAt(1)));
                    }
                }
                MultiplyCache.getInstance().clear();
                MultiplyCache.getInstance().setNums(i1,i2,i3,i4);
                startActivity(new Intent(FormActivity.this, MultiplyActivity.class));
                finish();
            }
        });
    }

    private void setFormClick(){
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCorrect()){
                    MultiplyCache.getInstance().setFinalAns(String.valueOf(getUserAns()));
                    finish();
                }else{
                    userAns.startAnimation(shakeError());
                }
            }
        });
    }

    private boolean isCorrect(){
        int ans = num1 * num2;
        return ans == getUserAns();
    }

    private int getUserAns(){
        int i = 0;
        try{
            i = Integer.valueOf(userAns.getText().toString().trim());
        }catch (NumberFormatException e){
            i = 0;
        }
        return i;
    }
}
