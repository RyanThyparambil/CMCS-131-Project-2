import java.util.Scanner;
import java.util.Random;

public class Simulation {
    private Hospital hospital;
    private int currentTime;

    private int totalTime;
    private int timeStep;
    private int numPatients;

    private static Random random = new Random();

    public void setup() {
        Scanner input = new Scanner(System.in);

        System.out.println("Hospital Simulation Setup");

        System.out.print("Enter total simulation time: ");
        this.totalTime = input.nextInt();

        System.out.print("Enter time step in minutes: ");
        this.timeStep = input.nextInt();

        System.out.print("Enter number of patients to simulate:");
        this.numPatients = input.nextInt();

        this.hospital = new Hospital(numPatients);
        this.currentTime = 0;

        System.out.println("Setup complete. Hospital ready with " + numPatients + " patients.");
    }

    public void run() {
        System.out.println("\nRunning Simulation...");
        while (currentTime < totalTime) {
            System.out.println("Current Time: " + currentTime + " mins");


            currentTime += timeStep;
        }
        System.out.println("Simulation finished at " + currentTime + " minutes.");
    }

    public void process() {
        System.out.println("\n--- Final Results ---");
        System.out.println("Total Patients Number: " + hospital.getPatients().length);
        System.out.println("Total Time Elapsed: " + currentTime + " minutes.");
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public Hospital getHospital() {
        return hospital;
    }
}

