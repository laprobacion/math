package com.master.math.activity.addition;

import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;

import java.util.ArrayList;
import java.util.List;

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
        if(step.getStep() == AdditionStep.STEP_8){
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

    private boolean startValidate(){
        if(step.getStep() == AdditionStep.STEP_1){
            if(get1Id() == R.id.numUp4 && get1Id() == R.id.numDown4){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_2){
            if((get1Id() == R.id.winAns2 && get1Id() == R.id.topNum1) || (get2Id() == R.id.winAns1 && get1Id() == R.id.ans1)){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_3){
            if(get1Id() == R.id.numUp3 && get1Id() == R.id.numDown3){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_4){
            if((get1Id() == R.id.winAns2 && get1Id() == R.id.topNum2) || (get2Id() == R.id.winAns1 && get1Id() == R.id.ans2)){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_5){
            if(get1Id() == R.id.numUp2 && get1Id() == R.id.numDown2){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_6){
            if((get1Id() == R.id.winAns2 && get1Id() == R.id.topNum3) || (get2Id() == R.id.winAns1 && get1Id() == R.id.ans3)){
                return true;
            }
        }
        if(step.getStep() == AdditionStep.STEP_7){
            if(get1Id() == R.id.numUp1 && get1Id() == R.id.numDown1){
                return true;
            }
        }
        return false;
    }
}
