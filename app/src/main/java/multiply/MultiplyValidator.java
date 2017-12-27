package multiply;


import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;
import java.util.ArrayList;
import java.util.List;

import static com.master.math.activity.util.Util.shakeError;

public class MultiplyValidator {
    private List<TextView> draggableItems;

    private MultiplyStep step;
    private DraggedItem draggedItem;
    public MultiplyStep getStep(){
        return this.step;
    }
    public MultiplyValidator(MultiplyStep step){
        this.step = step;
        draggableItems = new ArrayList<TextView>();
    }

    public void addDraggableItems(TextView... i){
        for(TextView num : i){
            draggableItems.add(num);
        }
    }

    public boolean validate(DraggedItem draggedItem){
        if(isFinished()){
            return false;
        }
        this.draggedItem = draggedItem;
        return startValidate();
    }
    private boolean startValidate(){
        if(step.getStep() == MultiplyStep.STEP_1){
            if(get1Id() == R.id.num2 && get2Id() == R.id.num3){
                return true;
            }
            get(R.id.num2).startAnimation(shakeError());
            get(R.id.num3).startAnimation(shakeError());
        }
        if(step.getStep() == MultiplyStep.STEP_2){
            if(isBoxedEmpty()){
                if(get1Id() == R.id.num1 && get2Id() == R.id.num3){
                    return true;
                }
                get(R.id.num1).startAnimation(shakeError());
                get(R.id.num3).startAnimation(shakeError());
            }else{
                if(get1Id() == R.id.ans2 && get(R.id.topNum).getBackground() !=null && get2Id() == R.id.topNum){
                    return true;
                }
                boolean flag1 = false;
                boolean flag2 = false;
                if(get1Id() == R.id.ans2){
                    get(R.id.ans2).startAnimation(shakeError());
                    if(get(R.id.topNum).getVisibility() == View.VISIBLE){
                        get(R.id.topNum).startAnimation(shakeError());
                    }
                    flag1 = true;
                }
                if(get1Id() == R.id.ans1 && get2Id() == R.id.totalAns1){
                    return true;
                }
                if(get1Id() == R.id.ans1){
                    get(R.id.ans1).startAnimation(shakeError());
                    get(R.id.totalAns1).startAnimation(shakeError());
                    flag1 = true;
                }
                if(!flag1 && !flag2){
                    get(R.id.ans2).startAnimation(shakeError());
                    if(get(R.id.topNum).getVisibility() == View.VISIBLE){
                        get(R.id.topNum).startAnimation(shakeError());
                    }
                    get(R.id.ans1).startAnimation(shakeError());
                    get(R.id.totalAns1).startAnimation(shakeError());
                }
            }
        }
        if(step.getStep() == MultiplyStep.STEP_3){
            if(firstCarryTopNumEmpty()){
                if(get1Id() == R.id.ans2 && get2Id() == R.id.totalAns2){
                    return true;
                }
                get(R.id.ans2).startAnimation(shakeError());
                get(R.id.totalAns2).startAnimation(shakeError());
            }else{
                if(get1Id() == R.id.ans2 && get2Id() == R.id.topNum2){
                    return true;
                }
                if(get(R.id.topNum2).getVisibility() == View.VISIBLE){
                    get(R.id.topNum2).startAnimation(shakeError());
                }
            }
            if(get(R.id.totalAns2).getBackground() != null && get1Id() == R.id.ans2 && get2Id() == R.id.totalAns2){
                return true;
            }
            if(get(R.id.totalAns2).getBackground() != null && get1Id() == R.id.ans2 ){
                if(get(R.id.ans2).getVisibility() == View.VISIBLE){
                    get(R.id.ans2).startAnimation(shakeError());
                }
                get(R.id.totalAns2).startAnimation(shakeError());
            }
        }
        if(step.getStep() == MultiplyStep.STEP_4){
            if(get1Id() == R.id.ans2 && get2Id() == R.id.totalAns2){
                return true;
            }
            get(R.id.ans2).startAnimation(shakeError());
            get(R.id.totalAns2).startAnimation(shakeError());
        }
        if(step.getStep() == MultiplyStep.STEP_5){
            if(get1Id() == R.id.num2 && get2Id() == R.id.num4){
                return true;
            }
            get(R.id.num2).startAnimation(shakeError());
            get(R.id.num4).startAnimation(shakeError());
        }
        if(step.getStep() == MultiplyStep.STEP_6){
            if(isBoxedEmpty()){
                if(get1Id() == R.id.num1 && get2Id() == R.id.num4){
                    return true;
                }
                get(R.id.num1).startAnimation(shakeError());
                get(R.id.num4).startAnimation(shakeError());
            }else{
                if(get1Id() == R.id.ans2 && get(R.id.topNum3).getBackground() !=null && get2Id() == R.id.topNum3){
                    return true;
                }
                boolean flag1 = false;
                boolean flag2 = false;
                if(get1Id() == R.id.ans2 && get2Id() != R.id.topNum3){
                    get(R.id.ans2).startAnimation(shakeError());
                    if(get(R.id.topNum3).getVisibility() == View.VISIBLE){
                        get(R.id.topNum3).startAnimation(shakeError());
                    }
                    flag1 = true;
                }
                if(get1Id() == R.id.ans1 && get2Id() == R.id.totalAns3){
                    return true;
                }
                if(get1Id() == R.id.ans1 && get2Id() != R.id.totalAns3 ){
                    get(R.id.ans1).startAnimation(shakeError());
                    get(R.id.totalAns3).startAnimation(shakeError());
                    flag2 = true;
                }
                if(!flag1 && !flag2){
                    get(R.id.ans2).startAnimation(shakeError());
                    if(get(R.id.topNum3).getVisibility() == View.VISIBLE){
                        get(R.id.topNum3).startAnimation(shakeError());
                    }
                    get(R.id.ans1).startAnimation(shakeError());
                    get(R.id.totalAns3).startAnimation(shakeError());
                }
            }
        }
        if(step.getStep() == MultiplyStep.STEP_7){
            if(get1Id() == R.id.num1 && get2Id() == R.id.num4){
                return true;
            }
            get(R.id.num1).startAnimation(shakeError());
            get(R.id.num4).startAnimation(shakeError());
        }
        if(step.getStep() == MultiplyStep.STEP_8){
            if(get1Id() == R.id.ans2 && get2Id() == R.id.totalAns4){
                return true;
            }
            get(R.id.ans2).startAnimation(shakeError());
            get(R.id.totalAns4).startAnimation(shakeError());
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
    public TextView get(int id){
        for(TextView v : draggableItems){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
    private TextView get1(){
        return this.draggedItem.getItem(0);
    }
    private TextView get2(){
        return this.draggedItem.getItem(1);
    }
    private int get1Id(){return this.draggedItem.getItem(0).getId(); }
    private int get2Id(){
        return this.draggedItem.getItem(1).getId();
    }
    public boolean isBoxedEmpty(){
        return (get(R.id.ans1).getVisibility() == View.INVISIBLE && get(R.id.ans2).getVisibility() == View.INVISIBLE);
    }
    private boolean firstCarryTopNumEmpty(){
        return get(R.id.topNum).getText().toString() == "0";
    }
    public boolean isFinished(){
        if(get(R.id.add).getVisibility() == View.VISIBLE){
            removeListeners();
            return true;
        }
        return false;
    }
    public void removeListeners(){
        for(TextView v : draggableItems){
            v.setOnTouchListener(null);
            v.setOnDragListener(null);
        }
    }
}
