package multiply;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;

import com.master.math.R;

public class MultiplyInitializer {

    private AssetManager asset;
    private MultiplyValidator validator;
    private MultiplyProcessor processor;
    private MultiplyListener listener;
    public MultiplyInitializer(MultiplyStep step, MultiplyProcessor processor, AssetManager asset, TextView... view){
        this.asset = asset;
        this.validator = new MultiplyValidator(step);
        this.processor = processor;
        listener = new MultiplyListener(processor, validator);
        for(TextView v : view){
            setTextColors(v);
            setTypeFaces(v);
            if((v.getId() != R.id.line && v.getId() != R.id.multiply)) {
                setListeners(v);
            }
        }
    }
    public MultiplyValidator getValidator(){
        return this.validator;
    }
    private void setTextColors(TextView view){
        view.setTextColor(Color.argb(255, 255, 255, 255));
    }
    private void setTypeFaces(TextView view){
        view.setTypeface(Typeface.createFromAsset(asset,"fonts/EraserDust.ttf"));
    }

    public void setListeners(TextView view){

        view.setOnTouchListener(listener);
        view.setOnDragListener(listener);
    }
}
