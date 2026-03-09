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

        System.out.print("Enter number of nurses to simulate: ");
        int numNurses = input.nextInt();

        this.hospital = new Hospital(this.numPatients, numNurses);
        this.currentTime = 0;

        for (int i = 0; i < numPatients; i++) {
            hospital.addPatient(new Patient());
        }

        for (int i = 0; i < numNurses; i++) {
            hospital.addNurse(new Nurse("Nurse_" + (i + 1)));
        }

        System.out.println("\nSetup complete. Hospital ready.");
    }

    public void run() {
        if (hospital == null || hospital.getPatientCount() == 0) {
            System.out.println("Error: Run setup first.");
            return;
        }

        System.out.println("\n--- Starting Simulation ---");

        while (currentTime < totalTime) {
            System.out.println("\n====================================");
            System.out.println("TIME: " + currentTime + " minutes");
            System.out.println("====================================");

            for (int i = 0; i < hospital.getPatientCount(); i++) {
                Patient p = hospital.getPatient(i);
                if (p != null) {
                    System.out.println("Checking Patient: " + p.getID());
                    p.checkPatient(hospital);
                    p.pressBuzzer(hospital);
                }
            }

            System.out.println("\n--- Nurse Activity ---");
            hospital.dispatchStaff();

            currentTime += timeStep;
        }

        System.out.println("\n--- Simulation Complete ---");
    }

    public void process() {
        System.out.println("\n--- Final Simulation Summary ---");
        System.out.println("Total Time Simulated: " + totalTime + " minutes");
        System.out.println("Total Patients: " + hospital.getPatientCount());
        System.out.println("Total High Severity Alerts: " + hospital.getHighAlertCount());
        System.out.println("Total Manual Buzzer Calls: " + hospital.getManualCallCount());
        System.out.println("Total Low Severity Alerts: " + hospital.getLowAlertCount());
        System.out.println("---------------------------------");
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static int getCurrentTime() {
        return currentTime;
    }
}