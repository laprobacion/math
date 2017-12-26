package com.master.math.activity.addition;


public class AdditionStep {
    private int step;
    public static final int STEP_1 = 1;
    public static final int STEP_2 = 2;
    public static final int STEP_3 = 3;
    public static final int STEP_4 = 4;
    public static final int STEP_5 = 5;
    public static final int STEP_6 = 6;
    public static final int STEP_7 = 7;
    public static final int STEP_8 = 8;
    public static final int STEP_9 = 9;
    public AdditionStep(){
        step = 1;
    }
    public void increment(){
        step++;
    }
    public int getStep(){
        return step;
    }
    public void setStep(int step){
        this.step = step;
    }
}
