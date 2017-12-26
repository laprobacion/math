package multiply;


public class MultiplyCache {

    private static MultiplyCache _this;
    private int[] nums;
    public static MultiplyCache getInstance(){
        if(_this == null){
            _this = new MultiplyCache();
        }
        return _this;
    }
    private MultiplyCache(){}
    public void setNums(int... i){
        nums = i;
    }
    public int[] getNums(){
        return nums;
    }
}
