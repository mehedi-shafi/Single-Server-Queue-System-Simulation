package Model;

import java.util.Random;

/**
 * Created by Shafi on 4/8/2018.
 */
public class Unit {

    private int number;
    private String name;
    private int arrival;
    private int departure;
    private int taskStarted;
    private int randomNumberForService;
    private Random rand = new Random();
    private int seriveTime;
    private int waitingTime;

    public Unit (){
        this.randomNumberForService = rand.nextInt(100)+1;
    }

    public Unit (int number, int arrival){
        this.number = number;
        this.arrival = arrival;
        this.randomNumberForService = rand.nextInt(100)+1;
    }

    public int getSeriveTime() {
        return seriveTime;
    }

    public void setSeriveTime(int seriveTime) {
        this.seriveTime = seriveTime;
    }

    public int getRandomNumberForService() {
        return randomNumberForService;
    }

    public void setRandomNumberForService(int randomNumberForService) {
        this.randomNumberForService = randomNumberForService;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getTaskStarted() {
        return taskStarted;
    }

    public void setTaskStarted(int taskStarted) {
        this.taskStarted = taskStarted;
    }
}
