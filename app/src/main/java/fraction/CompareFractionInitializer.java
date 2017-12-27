package fraction;


import android.widget.TextView;

public class CompareFractionInitializer {
    private CompareFractionValidator validator;
    private CompareFractionListener listener;
    public CompareFractionInitializer(CompareFractionStep step, CompareFractionProcessor processor, TextView... view){
        this.validator = new CompareFractionValidator(step);
        listener = new CompareFractionListener(processor, validator);
        for(TextView v : view){
            setListeners(v);
        }
    }
    public CompareFractionValidator getValidator(){
        return this.validator;
    }

    public void setListeners(TextView view){

        view.setOnTouchListener(listener);
        view.setOnDragListener(listener);
    }
}
