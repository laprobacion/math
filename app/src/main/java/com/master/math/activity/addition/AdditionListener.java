package com.master.math.activity.addition;


import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.util.DraggedItem;

public class AdditionListener  implements View.OnDragListener, View.OnTouchListener{
    DraggedItem draggedItem;
    AdditionProcessor processor;
    AdditionValidator validator;

    public AdditionListener(AdditionProcessor processor, AdditionValidator validator){
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
                    }

                }else{
                    if(draggedItem.getItem(0).getId() == R.id.winAns1 || draggedItem.getItem(0).getId() == R.id.winAns2){
                        ((TextView) v).setVisibility(View.VISIBLE);
                        v.invalidate();
                    }
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
