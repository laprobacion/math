package com.master.math.activity.addition;

import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;

import java.util.ArrayList;
import java.util.List;

import static com.master.math.activity.util.Util.shakeError;

public class AdditionValidator {
    private List<TextView> draggableItems;
    private DraggedItem draggedItem;
    private AdditionStep step;

    public AdditionStep getStep() {
        return step;
    }

    public AdditionValidator(AdditionStep step){
        this.step = step;
        draggableItems = new ArrayList<TextView>();
    }
    public void addDraggableItems(TextView... i){
        for(TextView num : i){
            draggableItems.add(num);
        }
    }
    public boolean validate(DraggedItem draggedItem){
        if(step.getStep() == AdditionStep.STEP_9){
            return false;
        }
        this.draggedItem = draggedItem;
        return startValidate();
    }
    private TextView get1(){
        return this.draggedItem.getItem(0);
    }
    private TextView get2(){
        return this.draggedItem.getItem(1);
    }
    private int get1Id(){
        return this.draggedItem.getItem(0).getId();
    }
    private int get2Id(){
        return this.draggedItem.getItem(1).getId();
    }
    public TextView get(int id){
        for(TextView v : draggableItems){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
    private boolean startValidate(){
        if(step.getStep() == AdditionStep.STEP_1){
            if(get1Id() == R.id.numUp1 && get2Id() == R.id.numDown1){
                return true;
            }
            get(R.id.numUp1).startAnimation(shakeError());
            get(R.id.numDown1).startAnimation(shakeError());
        }
        if(step.getStep() == AdditionStep.STEP_2){
            if((get1Id() == R.id.winAns2 && get2Id() == R.id.topNum1) || (get1Id() == R.id.winAns1 && get2Id() == R.id.ans1)){
                return true;
            }
            shakeValidValues(R.id.winAns2,R.id.topNum1,R.id.winAns1,R.id.ans1);
        }
        if(step.getStep() == AdditionStep.STEP_3){
            if(get1Id() == R.id.numUp2 && get2Id() == R.id.numDown2){
                return true;
            }
            get(R.id.numUp2).startAnimation(shakeError());
            get(R.id.numDown2).startAnimation(shakeError());
        }
        if(step.getStep() == AdditionStep.STEP_4){
            if((get1Id() == R.id.winAns2 && get2Id() == R.id.topNum2) || (get1Id() == R.id.winAns1 && get2Id() == R.id.ans2)){
                return true;
            }
            shakeValidValues(R.id.winAns2,R.id.topNum2,R.id.winAns1,R.id.ans2);
        }
        if(step.getStep() == AdditionStep.STEP_5){
            if(get1Id() == R.id.numUp3 && get2Id() == R.id.numDown3){
                return true;
            }
            get(R.id.numUp3).startAnimation(shakeError());
            get(R.id.numDown3).startAnimation(shakeError());
        }
        if(step.getStep() == AdditionStep.STEP_6){
            if((get1Id() == R.id.winAns2 && get2Id() == R.id.topNum3) || (get1Id() == R.id.winAns1 && get2Id() == R.id.ans3)){
                return true;
            }
            shakeValidValues(R.id.winAns2,R.id.topNum3,R.id.winAns1,R.id.ans3);
        }
        if(step.getStep() == AdditionStep.STEP_7){
            if(get1Id() == R.id.numUp4 && get2Id() == R.id.numDown4){
                return true;
            }
            get(R.id.numUp4).startAnimation(shakeError());
            get(R.id.numDown4).startAnimation(shakeError());
        }
        if(step.getStep() == AdditionStep.STEP_8){
            if(get1Id() == R.id.winAns2 && get2Id() == R.id.ans4){
                return true;
            }
            get(R.id.winAns2).startAnimation(shakeError());
            get(R.id.ans4).startAnimation(shakeError());
        }
        return invalidateFalse();
    }
    private boolean invalidateFalse(){
        get1().setVisibility(View.VISIBLE);
        get1().invalidate();
        get2().setVisibility(View.VISIBLE);
        get2().invalidate();
        return false;
    }

    private void shakeValidValues(int winAns2,int topNum,int winAns1,int ans ){
        if(get1Id() == winAns2){
            get(winAns2).startAnimation(shakeError());
            get(topNum).startAnimation(shakeError());
            return;
        }
        if(get1Id() == winAns1){
            get(winAns1).startAnimation(shakeError());
            get(ans).startAnimation(shakeError());
            return;
        }
        if(get(winAns2).getVisibility() == View.VISIBLE){
            get(winAns2).startAnimation(shakeError());
        }
        if(get(topNum).getVisibility() == View.VISIBLE){
            get(topNum).startAnimation(shakeError());
        }
        get(winAns1).startAnimation(shakeError());
        get(ans).startAnimation(shakeError());
    }
}
