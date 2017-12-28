package com.master.math.activity.lcd;

public class LCDCache {
    private static LCDCache _this;
    private boolean isFinished;
    private LCDCache(){}
    public static LCDCache get(){
        if(_this == null){
            _this = new LCDCache();
        }
        return _this;
    }

    public boolean isFinished() {        return isFinished;    }

    public void setFinished(boolean finished) {        isFinished = finished;    }
}
