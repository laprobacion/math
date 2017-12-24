package multiply;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.master.math.R;

import java.util.Random;

public class MultiplyProcessor {
    private TextView num1,num2,num3,multiply,line,formulaPop, ans1, ans2,topNum,topNum2, totalAns1, totalAns2, finalAnswer;
    private EditText userAns;
    private RelativeLayout popupMultiply;
    private MultiplyInitializer initializer;
    private Integer popupAnswer;
    private Activity activity;
    private MultiplyStep step;
    private int intFinalAnswer;
    public MultiplyProcessor(Activity activity, AssetManager asset, int... i){
        step = new MultiplyStep();
        this.activity = activity;
        userAns = (EditText) activity.findViewById(R.id.userAns);
        userAns.setTypeface(Typeface.createFromAsset(asset,"fonts/EraserDust.ttf"));
        finalAnswer = (TextView) activity.findViewById(R.id.finalAnswer);
        num1 = (TextView) activity.findViewById(R.id.num1);
        num2 = (TextView) activity.findViewById(R.id.num2);
        num3 = (TextView) activity.findViewById(R.id.num3);
        topNum = (TextView) activity.findViewById(R.id.topNum);
        topNum2 = (TextView) activity.findViewById(R.id.topNum2);
        multiply = (TextView) activity.findViewById(R.id.multiply);
        line = (TextView) activity.findViewById(R.id.line);
        ans1 = (TextView) activity.findViewById(R.id.ans1);
        ans2 = (TextView) activity.findViewById(R.id.ans2);
        totalAns1 = (TextView) activity.findViewById(R.id.totalAns1);
        totalAns2 = (TextView) activity.findViewById(R.id.totalAns2);
        formulaPop = (TextView) activity.findViewById(R.id.formulaPop);
        popupMultiply = (RelativeLayout) activity.findViewById(R.id.popupMultiply);
        this.initializer = new MultiplyInitializer(step,this, asset, num1,num2,num3,topNum,topNum2,multiply,line,ans1,ans2,formulaPop,totalAns1, totalAns2, finalAnswer);
        setNumbers(i);
        this.initializer.getValidator().addDraggableItems(num1,num2,num3,ans1,ans2,topNum,topNum2,totalAns1,totalAns2, finalAnswer);
        renderPopupWindow(false);
        topNum.setTextColor(Color.argb(2, 255, 255, 255));
        topNum2.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns1.setTextColor(Color.argb(2, 255, 255, 255));
        totalAns2.setTextColor(Color.argb(2, 255, 255, 255));
        renderAnswerWindow(false);
    }
    public boolean isPopupMultiplyVisible(){
        return popupMultiply.getVisibility() == View.VISIBLE;
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
            multiply.setVisibility(View.INVISIBLE);
            line.setVisibility(View.INVISIBLE);
        }else{
            num1.setVisibility(View.VISIBLE);
            num2.setVisibility(View.VISIBLE);
            num3.setVisibility(View.VISIBLE);
            multiply.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }
    }
    public void setFormulaPop(DraggedItem draggedItem){
        ((TextView) activity.findViewById(R.id.replay)).setVisibility(View.INVISIBLE);
        ((TextView) activity.findViewById(R.id.newProblem)).setVisibility(View.INVISIBLE);
        userAns.setText("");
        int n1 = Integer.valueOf(draggedItem.getItem(0).getText().toString());
        int n2 = Integer.valueOf(draggedItem.getItem(1).getText().toString());
        Integer ans = n1 * n2;
        if(step.getStep() == MultiplyStep.STEP_1 || step.getStep() == MultiplyStep.STEP_2) {
            formulaPop.setText(n1 + " x " + n2 + " =    " );
        }else if(step.getStep() == MultiplyStep.STEP_3){
            ans = Integer.valueOf(n1) + Integer.valueOf(topNum.getText().toString().trim());
            formulaPop.setText(n1 + " + " + topNum.getText().toString() + " =    ");

            setTopNum2(draggedItem);
        }
        formulaPop.invalidate();
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
    private void setNumbers(int... i){
        if(i.length != 0){
            num1.setText(String.valueOf(i[0]));
            num2.setText(String.valueOf(i[1]));
            num3.setText(String.valueOf(i[2]));
        }else{
            num1.setText(generateRandomNumbers());
            num2.setText(generateRandomNumbers());
            num3.setText(generateRandomNumbers());
        }
        intFinalAnswer = Integer.valueOf(num1.getText().toString() + num2.getText().toString()) * Integer.valueOf(num3.getText().toString());
    }
    private String generateRandomNumbers(){
        Random rand = new Random();
        Integer  n = rand.nextInt(9) + 1;
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
        }

    }
    public void renderAnswerWindow(boolean show){
        if(show){
            popupAnswer = Integer.valueOf(userAns.getText().toString().trim());
            ans2.setText("");
            ans1.setText("");
            if(step.getStep() == MultiplyStep.STEP_1) {
                if (popupAnswer != null) {
                    populateAnsBox();
                    totalAns1.setBackground(activity.getResources().getDrawable(R.drawable.border));
                    step.increment();
                }
            }else if(step.getStep() == MultiplyStep.STEP_2){
                if (popupAnswer != null) {
                    populateAnsBox();
                    step.increment();
                }
            }else if(step.getStep() == MultiplyStep.STEP_3){
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
        this.initializer.getValidator().removeListeners();
        ((TextView) activity.findViewById(R.id.replay)).setVisibility(View.VISIBLE);
        ((TextView) activity.findViewById(R.id.newProblem)).setVisibility(View.VISIBLE);
        MultiplyCache.getInstance().setNums(null);
    }
    private void setFin(int fin){
        if(fin == intFinalAnswer){
            finalAnswer.setTextColor(Color.argb(255, 0, 255, 0));
        }else{
            finalAnswer.setTextColor(Color.argb(255, 255, 0, 0));
        }
        finalAnswer.setText(String.valueOf(fin));
        finalAnswer.setVisibility(View.VISIBLE);
        finalAnswer.invalidate();
    }
    public void invalidateAll(){
        TextView [] views = {num1,num2,num3,topNum,topNum2,formulaPop,totalAns1, totalAns2};
        for(TextView v : views){
            v.setVisibility(View.VISIBLE);
            v.invalidate();
        }
    }

    public int getNum1(){
        return Integer.valueOf(num1.getText().toString());
    }
    public int getNum2(){
        return Integer.valueOf(num2.getText().toString());
    }
    public int getNum3(){
        return Integer.valueOf(num3.getText().toString());
    }
}
