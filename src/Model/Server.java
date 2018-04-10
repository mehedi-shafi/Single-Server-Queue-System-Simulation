package Model;


import Main.SystemSim;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shafi on 4/8/2018.
 */
public class Server {

    private ArrayList<RandomEngine> serviceTimes = new ArrayList<>();
    private int serverUptime;
    private int idleTime = 0;
    private int lastTaskEnded = 0;
    private boolean serverIdle;
    private int totalServiceTime = 0;
    private Scanner scanner = new Scanner(System.in);

    public Server(int clock){
        System.out.println("Server up and running.");
        this.serverUptime = clock;
        System.out.println("Do you want to input the random number ranges and service time for the range manually? (Y/N):");
        String choice = scanner.nextLine();
        choice = choice.toLowerCase();
        if (choice.compareTo("y") == 0 || choice.compareTo("yes") == 0){

        }
        else {
            System.out.println("Generating random range of service times.");
            addRandomWorks();
        }
        this.serverIdle = true; // at the begining server is idle
    }

    public void giveService(Unit unit, SystemSim system){
        unit.setTaskStarted(system.getSystemTime());
        unit.setSeriveTime(getServiceTime(unit.getRandomNumberForService()));
        unit.setDeparture(system.getSystemTime() + unit.getSeriveTime());
        if (system.getSystemTime() > lastTaskEnded){
            idleTime = system.getSystemTime() - lastTaskEnded;
        }
        lastTaskEnded = unit.getDeparture();
        totalServiceTime += unit.getSeriveTime();
        unit.setWaitingTime(unit.getTaskStarted()-unit.getArrival());
        system.setSystemTime(system.getSystemTime() + unit.getSeriveTime());
        system.addDoneUnit(unit);
        system.popFirst();
    }

    private int getServiceTime(int randValue){
        for (RandomEngine rnd: serviceTimes){
            if (randValue >= rnd.getMin() && randValue <= rnd.getMax()){
                return rnd.getServiceTime();
            }
        }
        return 0;
    }

    private void addRandomWorks(){
        this.serviceTimes.add(new RandomEngine(1, 25, 3));
        this.serviceTimes.add(new RandomEngine(26, 40, 4));
        this.serviceTimes.add(new RandomEngine(41, 65, 5));
        this.serviceTimes.add(new RandomEngine(66, 88, 6));
        this.serviceTimes.add(new RandomEngine(89, 100, 2));
    }

    public void setRandomRanges(ArrayList<Integer> upperValues, ArrayList<Integer> serviceTimesList){
        this.serviceTimes.clear();
        int lowerValue = 1;
        for (int i = 0; i < upperValues.size(); i++){
            this.serviceTimes.add(new RandomEngine(lowerValue, upperValues.get(i), serviceTimesList.get(i)));
            lowerValue = upperValues.get(i) + 1;
        }
    }

    private void takeRandomNumberForServiceInput(){
        ArrayList<Integer> upperValues = new ArrayList<>();
        ArrayList<Integer> serviceTimes = new ArrayList<>();
        System.out.println("number of ranges to input?");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            System.out.print("Upper value of the range: ");
            upperValues.add(scanner.nextInt());
            System.out.print("Service time for the range?: ");
            serviceTimes.add(scanner.nextInt());
        }
        setRandomRanges(upperValues, serviceTimes);
    }

    public boolean isServerIdle() {
        return serverIdle;
    }

    public void setServerIdle(boolean serverIdle) {
        this.serverIdle = serverIdle;
    }

    public ArrayList<RandomEngine> getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(ArrayList<RandomEngine> serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public int getServerUptime() {
        return serverUptime;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public void setServerUptime(int serverUptime) {
        this.serverUptime = serverUptime;
    }
}
