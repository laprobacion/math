package multiply;

import android.content.res.AssetManager;
import android.widget.TextView;

import com.master.math.R;

public class MultiplyInitializer {

    private MultiplyValidator validator;
    private MultiplyListener listener;
    public MultiplyInitializer(MultiplyStep step, MultiplyProcessor processor,TextView... view){
        this.validator = new MultiplyValidator(step);
        listener = new MultiplyListener(processor, validator);
        for(TextView v : view){
            if((v.getId() != R.id.line && v.getId() != R.id.multiply)) {
                setListeners(v);
            }
        }
    }
    public MultiplyValidator getValidator(){
        return this.validator;
    }

    public void setListeners(TextView view){

        view.setOnTouchListener(listener);
        view.setOnDragListener(listener);
    }
}
