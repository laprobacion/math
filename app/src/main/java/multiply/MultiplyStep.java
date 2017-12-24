package multiply;


public class MultiplyStep {
    private int step;
    public static final int STEP_1 = 1;
    public static final int STEP_2 = 2;
    public static final int STEP_3 = 3;
    public static final int STEP_4 = 4;
    public MultiplyStep(){
        step = 1;
    }
    public void increment(){
        step++;
    }
    public int getStep(){
        return step;
    }
}
