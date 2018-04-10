package Model;

import java.util.Random;

public class RandomEngine {

    private int min;
    private int max;
    private int serviceTime;

    public int getServiceTime(){
        return serviceTime;
    }

    public RandomEngine(int min, int max, int serviceTime){
        this.min = min;
        this.max = max;
        this.serviceTime = serviceTime;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }
}