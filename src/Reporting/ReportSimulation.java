package Reporting;

import Main.SystemSim;
import Model.Unit;

public class ReportSimulation {

    private SystemSim system;

    public ReportSimulation(SystemSim system){
        this.system = system;
        makeReport();
    }

    private void makeReport(){
        System.out.println("Report of the simulation.");
        System.out.println("Number of unit entered: " + (system.getOutUnits().size() + system.getWaitingQueue().size()));
        System.out.println("Number of units serviced: " + system.getOutUnits().size());
        System.out.println("Server IDLE time: " + system.getServer().getIdleTime());
        System.out.println("Server total service time: " + system.getServer().getTotalServiceTime());
        System.out.println("Total waiting time: " + getTotalWaitingTime());
        System.out.println("Service table");
        System.out.println("#\tClock\tArrival\tService Start\tService Time\tService End\t\tWaiting Time");
        for (Unit unit: system.getOutUnits()){
            System.out.println(unit.getNumber()+"\t"+unit.getArrival()+"\t\t"+unit.getArrival()+"\t\t"+unit.getTaskStarted()+"\t\t\t\t"+unit.getSeriveTime()+"\t\t\t\t"+unit.getDeparture()+"\t\t\t\t"+(unit.getWaitingTime()));
        }
    }

    private int getTotalWaitingTime(){
        int waitingTime = 0;
        for(Unit unit: system.getOutUnits()){
            waitingTime += unit.getWaitingTime();
        }
        return waitingTime;
    }

}