package Main;

import Model.RandomEngine;
import Model.Server;
import Model.Unit;
import Reporting.ReportSimulation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SystemSim {

    private int simulationTime;
    private int systemTime = 0; //clock time starts at 0
    private ArrayList<Unit> waitingQueue = new ArrayList<>();
    private Server server;
    private Random random = new Random();
    private ArrayList<Unit> outUnits = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<RandomEngine> interArrivalTimeTable = new ArrayList<>();

    public SystemSim(int simulationTime){
        this.simulationTime = simulationTime;
//        this.server = new Server(systemTime);
    }

    public SystemSim(){
        this.simulationTime = 24;
//        this.server = new Server(systemTime);
    }

    public void Simulate(){
        generateRandomWaitingQueue();
//        for (int i = 0; i < waitingQueue.size(); i++) System.out.println(waitingQueue.get(i).getArrival() + " " + waitingQueue.get(i).getNumber());
        startSimulation();
    }

    public void setRandomArrivalRange(){
        ArrayList<Integer> upperRange = new ArrayList<>();
        ArrayList<Integer> arrivalTime = new ArrayList<>();
        System.out.print("How many range you want to input?: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            System.out.print("Upper range: ");
            upperRange.add(scanner.nextInt());
            System.out.print("Inter-arrival time: ");
            arrivalTime.add(scanner.nextInt());
        }
        createInterarrivalTable(upperRange, arrivalTime);
    }

    private void createInterarrivalTable(ArrayList<Integer> upperBound, ArrayList<Integer> interArrivalTime){
        int lowerbound = 0;
        for (int i = 0; i < upperBound.size(); i++){
            this.interArrivalTimeTable.add(new RandomEngine(lowerbound, upperBound.get(i), interArrivalTime.get(i)));
            lowerbound = upperBound.get(i) + 1;
        }
        generateRandomWaitingQueueFromInterArrivalTable();
        startSimulation();
    }

    private void generateRandomWaitingQueueFromInterArrivalTable(){
        int tempClock = getSystemTime();
        for (int i = 0; tempClock <= simulationTime; i++){
            waitingQueue.add(new Unit(i, tempClock));
            tempClock += getArrivalTimeFromRandomDigit(random.nextInt()+1);
        }
    }

    private int getArrivalTimeFromRandomDigit(int randomDigit){
        for (RandomEngine rand : this.interArrivalTimeTable){
            if (randomDigit >= rand.getMin() && randomDigit <= rand.getMax()){
                return rand.getServiceTime();
            }
        }
        return 1;
    }

    private void startSimulation(){
        this.server = new Server(systemTime);
        while (systemTime <= simulationTime){
            server.giveService(waitingQueue.get(0), this);
        }
        new ReportSimulation(this);
    }

    private void generateRandomWaitingQueue(){
        int tempClock = getSystemTime();
        for (int i = 0; tempClock <= simulationTime; i++){
            int randomInterArrival = random.nextInt(5)+1;
            waitingQueue.add(new Unit(i, tempClock));
            tempClock += randomInterArrival;
        }
    }

    public int getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(int systemTime) {
        this.systemTime = systemTime;
    }

    public void addDoneUnit(Unit unit){
        this.outUnits.add(unit);
    }

    public ArrayList<Unit> getOutUnits(){
        return this.outUnits;
    }

    public void popFirst(){
        this.waitingQueue.remove(0);
    }

    public ArrayList<Unit> getWaitingQueue(){
        return this.waitingQueue;
    }

    public Server getServer() {
        return server;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }
}