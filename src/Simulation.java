import java.util.Scanner;

public class Simulation {

    private Hospital hospital;
    private static int currentTime;
    private int totalTime;
    private int timeStep;
    private int numPatients;
    private int numNurses;
    private static int resolutionChance;

    public void setup() {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Hospital Simulation Setup ---");

        System.out.print("Enter total simulation time (mins): ");
        this.totalTime = input.nextInt();

        System.out.print("Enter time step (mins): ");
        this.timeStep = input.nextInt();

        System.out.print("Enter number of patients: ");
        this.numPatients = input.nextInt();

        System.out.print("Enter number of nurses: ");
        this.numNurses = input.nextInt();

        System.out.print("Enter nurse resolution probability (1-100): ");
        resolutionChance = input.nextInt();   // <-- sets the global static value

        this.hospital = new Hospital(numPatients, numNurses);
        currentTime = 0;

        for (int i = 0; i < numPatients; i++) {
            hospital.addPatient(new Patient());
        }

        for (int i = 0; i < numNurses; i++) {
            hospital.addNurse(new Nurse("Nurse_" + (i + 1)));
        }
    }

    public void run() {
        if (hospital == null || hospital.getPatientCount() == 0) {
            System.out.println("Simulation not properly initialized.");
            return;
        }

        while (currentTime < totalTime) {
            System.out.println("\nTIME: " + currentTime);

            for (int i = 0; i < hospital.getPatientCount(); i++) {
                Patient p = hospital.getPatient(i);
                if (p != null) {
                    p.checkPatient(hospital);
                    p.pressBuzzer(hospital);
                }
            }

            hospital.dispatchStaff();

            currentTime += timeStep;
        }
    }

    public void process() {
        System.out.println("\n--- Final Summary ---");
        System.out.println("High Alerts Processed: " + hospital.getHighAlertCount());
        System.out.println("Low Alerts Processed: " + hospital.getLowAlertCount());
        System.out.println("Manual Alerts Processed: " + hospital.getManualAlertCount());
    }

    public static int getRandomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static int getResolutionChance() {
        return resolutionChance;
    }

    public static int getCurrentTime() {
        return currentTime;
    }

    public void incrementTime() {
        currentTime++;
    }
}
