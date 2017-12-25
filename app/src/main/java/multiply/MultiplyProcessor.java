package multiply;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.master.math.R;

import java.util.Random;

public class MultiplyProcessor {
    private TextView num1,num2,num3,num4,multiply,line,formulaPop, ans1, ans2,topNum,topNum2,topNum3, totalAns1, totalAns2,totalAns3,totalAns4, finalAnswerGroup1;
    private EditText userAns;
    private RelativeLayout popupMultiply,parentMultiply;
    private MultiplyInitializer initializer;
    private Integer popupAnswer;
    private Activity activity;
    private MultiplyStep step;
    private int formulaPopAnswer;
    public MultiplyProcessor(Activity activity, AssetManager asset, int... i){
        step = new MultiplyStep();
        this.activity = activity;
        userAns = (EditText) activity.findViewById(R.id.userAns);
        userAns.setTypeface(Typeface.createFromAsset(asset,"fonts/EraserDust.ttf"));
        finalAnswerGroup1 = (TextView) activity.findViewById(R.id.finalAnswerGroup1);
        num1 = (TextView) activity.findViewById(R.id.num1);
        num2 = (TextView) activity.findViewById(R.id.num2);
        num3 = (TextView) activity.findViewById(R.id.num3);
        num4 = (TextView) activity.findViewById(R.id.num4);
        topNum = (TextView) activity.findViewById(R.id.topNum);
        topNum2 = (TextView) activity.findViewById(R.id.topNum2);
        topNum3 = (TextView) activity.findViewById(R.id.topNum3);
        multiply = (TextView) activity.findViewById(R.id.multiply);
        line = (TextView) activity.findViewById(R.id.line);
        ans1 = (TextView) activity.findViewById(R.id.ans1);
        ans2 = (TextView) activity.findViewById(R.id.ans2);
        totalAns1 = (TextView) activity.findViewById(R.id.totalAns1);
        totalAns2 = (TextView) activity.findViewById(R.id.totalAns2);
        totalAns3 = (TextView) activity.findViewById(R.id.totalAns3);
        totalAns4 = (TextView) activity.findViewById(R.id.totalAns4);
        formulaPop = (TextView) activity.findViewById(R.id.formulaPop);
        popupMultiply = (RelativeLayout) activity.findViewById(R.id.popupMultiply);
        parentMultiply = (RelativeLayout) activity.findViewById(R.id.parentMultiply);
        this.initializer = new MultiplyInitializer(step,this, asset, num1,num2,num3,num4,topNum,topNum2,topNum3,multiply,line,ans1,ans2,totalAns1,totalAns2,totalAns3,totalAns4,finalAnswerGroup1);
        setNumbers(i);
        this.initializer.getValidator().addDraggableItems(num1,num2,num3,ans1,ans2,num4,topNum,topNum2,topNum3,totalAns1,totalAns2,totalAns3,totalAns4,finalAnswerGroup1);
        renderPopupWindow(false);
        topNum.setTextColor(Color.argb(2, 255, 255, 255));
        topNum2.setTextColor(Color.argb(2, 255, 255, 255));
        topNum3.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns1.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns2.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns3.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns4.setTextColor(Color.argb(2, 255, 255, 255));
        renderAnswerWindow(false);
    }
    private void setFormulaPopAnswer(int ans){
        this.formulaPopAnswer = ans;
    }
    public boolean isPopupMultiplyVisible(){
        return popupMultiply.getVisibility() == View.VISIBLE;
    }
    private void animateLayout(boolean show){
        AlphaAnimation clear = new AlphaAnimation(100, 100);
        clear.setDuration(0); // Make animation instant
        clear.setFillAfter(true); // Tell it to persist after the animation ends
        AlphaAnimation fade = new AlphaAnimation(0.5F, 0.5F);
        fade.setDuration(0); // Make animation instant
        fade.setFillAfter(true); // Tell it to persist after the animation ends
        if(show){
            popupMultiply.startAnimation(clear);
            parentMultiply.startAnimation(fade);
        }else {
            popupMultiply.startAnimation(fade);
            parentMultiply.startAnimation(clear);
        }
    }
    public void renderPopupWindow(boolean show){
        if(show){
            popupMultiply.setVisibility(View.VISIBLE);
            formulaPop.setVisibility(View.VISIBLE);
            userAns.setVisibility(View.VISIBLE);
        }else{
            userAns.setVisibility(View.INVISIBLE);
            popupMultiply.setVisibility(View.INVISIBLE);
            formulaPop.setVisibility(View.INVISIBLE);
        }
        fadeBackground(show);
    }


    private void fadeBackground(boolean isFade){
        if(isFade){
            num1.setVisibility(View.INVISIBLE);
            num2.setVisibility(View.INVISIBLE);
            num3.setVisibility(View.INVISIBLE);
            num4.setVisibility(View.INVISIBLE);
            multiply.setVisibility(View.INVISIBLE);
            line.setVisibility(View.INVISIBLE);
        }else{
            num1.setVisibility(View.VISIBLE);
            num2.setVisibility(View.VISIBLE);
            num3.setVisibility(View.VISIBLE);
            if(!num4.getText().toString().equals("0")) {
                num4.setVisibility(View.VISIBLE);
            }
            multiply.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }
    }
    public void setFormulaPop(DraggedItem draggedItem){
        userAns.setText("");
        formulaPop.setText("");
        int n1 = Integer.valueOf(draggedItem.getItem(0).getText().toString());
        int n2 = Integer.valueOf(draggedItem.getItem(1).getText().toString());
        Integer ans = n1 * n2;
        if(step.getStep() == MultiplyStep.STEP_1 || step.getStep() == MultiplyStep.STEP_2 || step.getStep() == MultiplyStep.STEP_5 || step.getStep() == MultiplyStep.STEP_7 ) {
            formulaPop.setText(n1 + " x " + n2 + " =      " );
        }else if(step.getStep() == MultiplyStep.STEP_8){
            ans = Integer.valueOf(n1) + Integer.valueOf(topNum3.getText().toString().trim());
            formulaPop.setText(n1 + " + " + topNum3.getText().toString() + " =      ");
            setTotalAns4(draggedItem, ans);
        }else if(step.getStep() == MultiplyStep.STEP_3){
            ans = Integer.valueOf(n1) + Integer.valueOf(topNum.getText().toString().trim());
            formulaPop.setText(n1 + " + " + topNum.getText().toString() + " =      ");

            setTopNum2(draggedItem);
        }
        setFormulaPopAnswer(ans);
        formulaPop.invalidate();
    }

    public boolean isFormulaPopAnsCorrect(){
        return this.formulaPopAnswer == Integer.valueOf(userAns.getText().toString());
    }
    private void setTopNum2(DraggedItem draggedItem){
        if(draggedItem.getItem(1).getId() == R.id.topNum2){
            topNum2.setText(draggedItem.getItem(0).getText());
            topNum2.setTextColor(Color.argb(255, 255, 255, 255));
            topNum2.setVisibility(View.VISIBLE);
            draggedItem.getItem(0).setVisibility(View.INVISIBLE);
            draggedItem.getItem(0).invalidate();
            topNum2.setBackground(null);
            topNum2.invalidate();
        }
    }
    public boolean setTopNum(DraggedItem draggedItem){
        if(draggedItem.getItem(1).getId() == R.id.topNum){
            topNum.setText(draggedItem.getItem(0).getText());
            topNum.setTextColor(Color.argb(255, 255, 255, 255));
            topNum.setVisibility(View.VISIBLE);
            draggedItem.getItem(0).setVisibility(View.INVISIBLE);
            draggedItem.getItem(0).invalidate();
            topNum.setBackground(null);
            topNum.invalidate();
            return true;
        }
        return false;
    }
    public boolean setTopNum3(DraggedItem draggedItem){
        if(draggedItem.getItem(1).getId() == R.id.topNum3){
            topNum3.setText(draggedItem.getItem(0).getText());
            topNum3.setTextColor(Color.argb(255, 255, 255, 255));
            topNum3.setVisibility(View.VISIBLE);
            draggedItem.getItem(0).setVisibility(View.INVISIBLE);
            draggedItem.getItem(0).invalidate();
            topNum3.setBackground(null);
            topNum3.setTextSize(33);
            topNum3.invalidate();
            if(this.initializer.getValidator().isBoxedEmpty()){
                step.increment();
            }
            return true;
        }
        return false;
    }
    private void setNumbers(int... i){
        if(i.length != 0){
            num1.setText(String.valueOf(i[0]));
            num2.setText(String.valueOf(i[1]));
            num3.setText(String.valueOf(i[2]));
            if(i.length == 4){
                setNum4(String.valueOf(i[3]));
            }
        }else{
            num1.setText(generateRandomNumbers(false));
            num2.setText(generateRandomNumbers(false));
            num3.setText(generateRandomNumbers(false));
            setNum4("");
        }
    }
    private void setNum4(String n){
        int num = n.equals("") ? Integer.valueOf(generateRandomNumbers(true)) : Integer.valueOf(n);
        if(num != 0){
            num4.setVisibility(View.VISIBLE);
        }else{
            num4.setVisibility(View.INVISIBLE);
        }
        num4.setText(String.valueOf(num));
        num4.invalidate();
    }
    private String generateRandomNumbers(boolean includeZero){
        Random rand = new Random();
        Integer  n = 0;
        if(!includeZero){
            n = rand.nextInt(9) + 1;
        }else{
            n = rand.nextInt(9);
        }
        return n.toString();
    }

    private void populateAnsBox(){
        String strAns = popupAnswer.toString();
        if(step.getStep() == MultiplyStep.STEP_1) {
            if (strAns.length() == 2) {
                ans2.setText(Character.toString(strAns.charAt(0)));
                ans1.setText(Character.toString(strAns.charAt(1)));
                ans1.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.VISIBLE);
                topNum.setBackground(activity.getResources().getDrawable(R.drawable.border));
            } else {
                ans1.setText(Character.toString(strAns.charAt(0)));
                ans1.setVisibility(View.VISIBLE);
            }
            totalAns1.setBackground(activity.getResources().getDrawable(R.drawable.border));
        }else if(step.getStep() == MultiplyStep.STEP_2){
            if (!topNum.getText().equals("0")) {
                topNum2.setBackground(activity.getResources().getDrawable(R.drawable.border));
            }else{
                totalAns2.setBackground(activity.getResources().getDrawable(R.drawable.border));
            }
            ans2.setText(strAns);
            ans2.setVisibility(View.VISIBLE);
        }else if(step.getStep() == MultiplyStep.STEP_3){
            totalAns2.setBackground(activity.getResources().getDrawable(R.drawable.border));
            ans2.setText(strAns);
            ans2.setVisibility(View.VISIBLE);
        }else if(step.getStep() == MultiplyStep.STEP_5){
            if (strAns.length() == 2) {
                ans2.setText(Character.toString(strAns.charAt(0)));
                ans1.setText(Character.toString(strAns.charAt(1)));
                ans1.setVisibility(View.VISIBLE);
                ans2.setVisibility(View.VISIBLE);
                topNum3.setBackground(activity.getResources().getDrawable(R.drawable.border));
            } else {
                ans1.setText(Character.toString(strAns.charAt(0)));
                ans1.setVisibility(View.VISIBLE);
            }
            totalAns3.setBackground(activity.getResources().getDrawable(R.drawable.border));
        }else if(step.getStep() == MultiplyStep.STEP_7){
            ans2.setText(strAns);
            ans2.setVisibility(View.VISIBLE);
            totalAns4.setBackground(activity.getResources().getDrawable(R.drawable.border));
        }

    }
    public void renderAnswerWindow(boolean show){
        if(show){
            popupAnswer = Integer.valueOf(userAns.getText().toString().trim());
            ans2.setText("");
            ans1.setText("");
            if(step.getStep() >= MultiplyStep.STEP_1 && step.getStep() <= MultiplyStep.STEP_5 || step.getStep() ==  MultiplyStep.STEP_7){
                if (popupAnswer != null) {
                    populateAnsBox();
                    step.increment();
                }
            }
        }else{
            ans1.setVisibility(View.INVISIBLE);
            ans2.setVisibility(View.INVISIBLE);
        }
    }


    public void setTotalAns1(DraggedItem draggedItem){
        totalAns1.setText(draggedItem.getItem(0).getText());
        totalAns1.setTextColor(Color.argb(255, 255, 255, 255));
        totalAns1.setVisibility(View.VISIBLE);
        draggedItem.getItem(0).setVisibility(View.INVISIBLE);
        draggedItem.getItem(0).invalidate();
        totalAns1.setBackground(null);
        totalAns1.invalidate();

    }
    public void setTotalAns2(DraggedItem draggedItem){
        setFin(Integer.valueOf(draggedItem.getItem(0).getText().toString() + totalAns1.getText().toString()));
        draggedItem.getItem(0).setVisibility(View.INVISIBLE);
        draggedItem.getItem(0).invalidate();
        totalAns1.setVisibility(View.INVISIBLE);
        totalAns2.setVisibility(View.INVISIBLE);
        totalAns2.invalidate();
        totalAns1.invalidate();
        totalAns2.invalidate();
        MultiplyCache.getInstance().setNums(null);
        step.setStep(MultiplyStep.STEP_5);
    }
    public void setTotalAns3(DraggedItem draggedItem){
        String txt = "";
        if(finalAnswerGroup1.getText().toString().length() == 3){
            txt = " " + draggedItem.getItem(0).getText() + "0";
        }else{
            txt = draggedItem.getItem(0).getText() + "0";
        }
        totalAns3.setText(txt);
        totalAns3.setTextColor(Color.argb(255, 255, 255, 255));
        totalAns3.setVisibility(View.VISIBLE);
        draggedItem.getItem(0).setVisibility(View.INVISIBLE);
        draggedItem.getItem(0).invalidate();
        totalAns3.setBackground(null);
        totalAns3.invalidate();
        if(this.initializer.getValidator().isBoxedEmpty()){
            step.increment();
        }
    }
    public void setTotalAns4(DraggedItem draggedItem, int ans){
        topNum3.setText(topNum3.getText().toString() + " + " + draggedItem.getItem(0).getText().toString() + " = " + ans);
        String under = String.valueOf(ans) + totalAns3.getText().toString().trim();
        String above = finalAnswerGroup1.getText().toString().trim();
        int diff = under.length() - above.length();
        String spaces = "";
        for(int i=0; i < diff; i++){
            spaces += " ";
        }
        finalAnswerGroup1.setText(spaces + above);
        totalAns3.setText(under);
        totalAns4.setVisibility(View.INVISIBLE);
        topNum3.setTextSize(20);
        topNum3.invalidate();
        totalAns4.invalidate();
        totalAns3.invalidate();
        draggedItem.getItem(0).setVisibility(View.INVISIBLE);
        draggedItem.getItem(0).invalidate();
        step.increment();
    }
    private void setFin(int fin){
        finalAnswerGroup1.setTextColor(Color.argb(255, 255, 255, 255));
        String txt = "";
        if(String.valueOf(fin).length() == 2){
            txt = " " + String.valueOf(fin);
        }else{
            txt = String.valueOf(fin);
        }
        finalAnswerGroup1.setText(txt);
        finalAnswerGroup1.setVisibility(View.VISIBLE);
        finalAnswerGroup1.setOnClickListener(null);
        finalAnswerGroup1.setOnTouchListener(null);
        finalAnswerGroup1.setOnDragListener(null);
        finalAnswerGroup1.invalidate();
    }
    public void invalidateAll(DraggedItem draggedItem){
        for(TextView v : draggedItem.dragItems){
            v.setVisibility(View.VISIBLE);
            v.invalidate();
        }
        draggedItem.clear();
    }

}
