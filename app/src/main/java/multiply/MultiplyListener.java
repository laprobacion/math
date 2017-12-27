package multiply;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;

public class MultiplyListener implements View.OnDragListener, View.OnTouchListener{
    DraggedItem draggedItem;
    MultiplyProcessor processor;
    MultiplyValidator validator;

    public MultiplyListener(MultiplyProcessor processor, MultiplyValidator validator){
        draggedItem = new DraggedItem();
        this.processor = processor;
        this.validator = validator;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        draggedItem.add(0,(TextView) view);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", ((TextView)view).getText().toString());
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        String t = ((TextView)v).getText().toString();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                draggedItem.add(1,(TextView) v);
                ((TextView) v).setVisibility(View.INVISIBLE);
                v.invalidate();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                ((TextView) v).setVisibility(View.VISIBLE);
                v.invalidate();
                break;
            case DragEvent.ACTION_DROP:
                if(validator.isFinished()){
                    break;
                }
                if(draggedItem.size() == 2) {
                    if(!validator.validate(draggedItem)){
                        processor.renderPopupWindow(null,false);
                        break;
                    }
                    if(processor.setTopNum(draggedItem)){
                        processor.renderPopupWindow(null, false);
                        draggedItem.clear();
                    }else if(processor.setTopNum3(draggedItem)){
                        processor.renderPopupWindow(null, false);
                        draggedItem.clear();
                    }else {
                        if(draggedItem.getItem(0).getId() == R.id.ans1 && draggedItem.getItem(1).getId() == R.id.totalAns1){
                            processor.setTotalAns1(draggedItem);
                        }else if(draggedItem.getItem(0).getId() == R.id.ans2 && draggedItem.getItem(1).getId() == R.id.totalAns2){
                            processor.setTotalAns2(draggedItem);
                        }else if(draggedItem.getItem(0).getId() == R.id.ans1 && draggedItem.getItem(1).getId() == R.id.totalAns3){
                            processor.setTotalAns3(draggedItem);
                        }else{
                            if((validator.getStep().getStep() == MultiplyStep.STEP_8 || validator.getStep().getStep() == MultiplyStep.STEP_9)
                                    && validator.get(R.id.topNum3).getText().toString().indexOf("0") == 0){
                                if(validator.getStep().getStep() == MultiplyStep.STEP_8){
                                    processor.setTotalAns4(draggedItem);
                                }
                                processor.renderPopupWindow(null, false);
                            }else{
                                processor.renderPopupWindow(draggedItem, true);
                            }

                        }
                    }
                }else{
                    if(draggedItem.getItem(0).getId() == R.id.ans1 || draggedItem.getItem(0).getId() == R.id.ans2){
                        ((TextView) v).setVisibility(View.VISIBLE);
                        v.invalidate();
                    }
                    processor.renderPopupWindow(null, false);
                    draggedItem.clear();
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}
