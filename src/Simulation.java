import java.util.Scanner;
import java.util.Random;

public class Simulation {
    private Hospital hospital;
    private static int currentTime;
    private static Random random = new Random();

    private int totalTime;
    private int timeStep;
    private int numPatients;


    public void setup() {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Hospital Simulation Setup ---");

        System.out.print("Enter total simulation time (mins): ");
        this.totalTime = input.nextInt();

        System.out.print("Enter time step (mins): ");
        this.timeStep = input.nextInt();

        System.out.print("Enter number of patients to simulate: ");
        this.numPatients = input.nextInt();

        // Added prompt for Nurse count to satisfy the Hospital(int, int) constructor
        System.out.print("Enter number of nurses to simulate: ");
        int numNurses = input.nextInt();

        // Now calling the constructor with BOTH arguments
        this.hospital = new Hospital(this.numPatients, numNurses);
        this.currentTime = 0;

        System.out.println("\nSetup complete.");
        System.out.println("Hospital initialized with " + numPatients + " patients and " + numNurses + " nurses.");
    }

    public void run() {
        System.out.println("\n--- Starting Simulation ---");

        for (int i = 0; i < numPatients; i++) {
            hospital.addPatient(new Patient());
        }

        int numNurses = (numPatients / 5) + 1;
        for (int i = 0; i < numNurses; i++) {
            hospital.addNurse(new Nurse("Nurse_" + (i + 1)));
        }

        while (currentTime < totalTime) {
            System.out.println("\n[Time: " + currentTime + " minutes]");

           for (int i = 0; i < hospital.getPatientCount(); i++) {
                Patient p = hospital.getPatient(i);
                if (p != null) {
                    p.checkPatient(hospital);
                }
            }

            hospital.dispatchStaff();
            currentTime += timeStep;
        }

        System.out.println("\n--- Simulation Complete ---");
    }

    public void process() {
        System.out.println("\n--- Final Simulation Summary ---");
        System.out.println("Total Time Simulated: " + totalTime + " minutes");
        System.out.println("Time Step: " + timeStep + " minutes");
        System.out.println("Total Patients: " + hospital.getPatientCount());

        System.out.println("Total High Severity Alerts: " + hospital.getHighAlertCount());
        System.out.println("Total Low Severity Alerts: " + hospital.getLowAlertCount());

        System.out.println("---------------------------------");
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public Hospital getHospital() {
        return hospital;
    }
    public static int getCurrentTime(){
        return currentTime;
    }


}

