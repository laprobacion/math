package com.master.math.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.master.math.R;
import com.master.math.activity.lcd.LCDProcessor;
import com.master.math.activity.util.Util;

public class LCDMainActivity extends AppCompatActivity {

    private LCDProcessor processor;
    private TextView getLCD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lcdmain);
        this.processor = new LCDProcessor(this);
        getLCD = Util.getTextViewWithFont(this, R.id.getLCD);
        final Intent intent = new Intent(this, LCDActivity.class);
        intent.putExtra(LCDActivity.NUM_1,Util.getTextViewWithFont(this, R.id.denom1).getText().toString());
        intent.putExtra(LCDActivity.NUM_2,Util.getTextViewWithFont(this, R.id.denom2).getText().toString());
        intent.putExtra(LCDActivity.NUM_3,Util.getTextViewWithFont(this, R.id.denom3).getText().toString());
        intent.putExtra(LCDActivity.LCD,this.processor.getLcd());
        Util.showWithText(getLCD,"Get LCD");
        getLCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
