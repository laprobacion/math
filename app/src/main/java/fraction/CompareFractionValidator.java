package fraction;


import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.addition.AdditionStep;
import com.master.math.activity.util.DraggedItem;

import java.util.ArrayList;
import java.util.List;

import static com.master.math.activity.util.Util.shakeError;

public class CompareFractionValidator {
    private List<TextView> draggableItems;
    private DraggedItem draggedItem;
    private CompareFractionStep step;

    public CompareFractionStep getStep() {
        return step;
    }

    public CompareFractionValidator(CompareFractionStep step){
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
    private boolean startValidate(){
        if(step.getStep() == CompareFractionStep.STEP_1){
            boolean flag = validateStep(R.id.num1, R.id.denom2);
            if(flag){
                return true;
            }else{
                get(R.id.num1).startAnimation(shakeError());
                get(R.id.denom2).startAnimation(shakeError());
            }
        }
        if(step.getStep() == CompareFractionStep.STEP_2){
            boolean flag = validateStep(R.id.num2, R.id.denom1);
            if(flag){
                return true;
            }else{
                get(R.id.num2).startAnimation(shakeError());
                get(R.id.denom1).startAnimation(shakeError());
            }
        }
        if(step.getStep() == CompareFractionStep.STEP_3){
            boolean flag1 = false,flag2 = false,flag3 = false;
            if(get1Id() == R.id.greaterSign && get2Id() != R.id.compareLine){
                get1().startAnimation(shakeError());
                get(R.id.compareLine).startAnimation(shakeError());
                flag1 = true;
            }
            if(get1Id() == R.id.greaterSign && get2Id() == R.id.compareLine){
                return true;
            }
            if(get1Id() == R.id.lessSign && get2Id() != R.id.compareLine){
                get1().startAnimation(shakeError());
                get(R.id.compareLine).startAnimation(shakeError());
                flag2 = true;
            }
            if(get1Id() == R.id.lessSign && get2Id() == R.id.compareLine){
                return true;
            }
            if(get1Id() == R.id.equalSign && get2Id() != R.id.compareLine){
                get1().startAnimation(shakeError());
                get(R.id.compareLine).startAnimation(shakeError());
                flag3 = true;
            }
            if(get1Id() == R.id.equalSign && get2Id() == R.id.compareLine){
                return true;
            }
            if(!flag1 && !flag2 && !flag3){
                get(R.id.greaterSign).startAnimation(shakeError());
                get(R.id.lessSign).startAnimation(shakeError());
                get(R.id.equalSign).startAnimation(shakeError());
            }
            if(get1Id() == R.id.compareLine){
                return false;
            }
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

    private boolean validateStep(int num, int denom){
        if(get1Id() == num && get2Id() == denom){
            return true;
        }
        if(get1Id() == num){
            get1().startAnimation(shakeError());
            get(denom).startAnimation(shakeError());
        }
        if(get1Id() == denom && get2Id() == num){
            return true;
        }
        if(get1Id() == denom){
            get1().startAnimation(shakeError());
            get(num).startAnimation(shakeError());
        }
        return false;
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
}
