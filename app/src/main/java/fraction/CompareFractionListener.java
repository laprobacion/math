package fraction;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;
import com.master.math.activity.util.Util;

public class CompareFractionListener implements View.OnDragListener, View.OnTouchListener{
    DraggedItem draggedItem;
    CompareFractionProcessor processor;
    CompareFractionValidator validator;

    public CompareFractionListener(CompareFractionProcessor processor, CompareFractionValidator validator){
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
                if(draggedItem.size() == 2) {
                    if(!validator.validate(draggedItem)){
                        break;
                    }else{
                        Util.showWithText(draggedItem.getItem(1),null);
                        processor.showPopup(draggedItem);
                        break;
                    }
                }else{
                    ((TextView) v).setVisibility(View.VISIBLE);
                    v.invalidate();
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
