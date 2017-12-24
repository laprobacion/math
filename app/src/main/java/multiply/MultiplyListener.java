package multiply;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.master.math.R;

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
                        processor.renderPopupWindow(false);
                        draggedItem.clear();
                        processor.invalidateAll();
                        break;
                    }
                    if(processor.setTopNum(draggedItem)){
                        processor.renderPopupWindow(false);
                        draggedItem.clear();
                    }else {
                        if(draggedItem.getItem(0).getId() == R.id.ans1 && draggedItem.getItem(1).getId() == R.id.totalAns1){
                            processor.setTotalAns1(draggedItem);
                        }else if(draggedItem.getItem(0).getId() == R.id.ans2 && draggedItem.getItem(1).getId() == R.id.totalAns2){
                            processor.setTotalAns2(draggedItem);
                        }else{
                            processor.setFormulaPop(draggedItem);
                            processor.renderPopupWindow(true);
                        }
                    }
                }else{
                    if(draggedItem.getItem(0).getId() == R.id.ans1 || draggedItem.getItem(0).getId() == R.id.ans2){
                        ((TextView) v).setVisibility(View.VISIBLE);
                        v.invalidate();
                    }
                    processor.renderPopupWindow(false);
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
