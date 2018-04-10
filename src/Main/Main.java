package Main;

import java.util.Scanner;

/**
 * Created by Shafi on 4/10/2018.
 */
public class Main {

    public static void main (String [] args){
        String choice = "yes";
        Scanner scanner = new Scanner(System.in);
        while (choice.compareTo("yes") == 0 || choice.compareTo("y") == 0){
            System.out.println("Welcome to Single Server Queue System Simulation.");
            System.out.println("Here you can put everything to system for generate randomly and show you an answer. Or be in the control.");
            System.out.println("Do you want start a simulation? (y/n)");
            choice = scanner.nextLine().toLowerCase();
            if (choice.compareTo("n") == 0 || choice.compareTo("no") == 0){
                return;
            }
            SystemSim system = new SystemSim();
            System.out.println("Do you want to see an automatic simulation with some random values? (y/n)");
            String choiceControl = scanner.nextLine().toLowerCase();
            if (choiceControl.compareTo("y") == 0 || choiceControl.compareTo("yes") == 0){
                system.Simulate();
            }
            else{
                System.out.print("Time to finish the simulation: ");
                int tempTime = scanner.nextInt();
                system.setSimulationTime(tempTime);
                scanner.nextLine();
                System.out.print("Generate random arrival units?(y/n)");
                String choiceControl2 = scanner.nextLine().toLowerCase();
                if (choiceControl2.compareTo("y") == 0 || choiceControl2.compareTo("yes") == 0){
                    system.Simulate();
                }
                else{
                    system.setRandomArrivalRange();
                }
            }
        }
    }

}
