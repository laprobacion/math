package com.master.math.activity.addition;


import android.widget.TextView;

import com.master.math.R;

public class AdditionInitializer {

    private AdditionValidator validator;
    private AdditionListener listener;
    public AdditionInitializer(AdditionStep step, AdditionProcessor processor,TextView... view){
        this.validator = new AdditionValidator(step);
        listener = new AdditionListener(processor, validator);
        for(TextView v : view){
            setListeners(v);
        }
    }
    public AdditionValidator getValidator(){
        return this.validator;
    }

    public void setListeners(TextView view){

        view.setOnTouchListener(listener);
        view.setOnDragListener(listener);
    }
}
