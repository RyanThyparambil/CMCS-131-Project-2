import java.util.Scanner;
import java.util.Random;

public class Simulation {
    private Hospital hospital;
    private static int currentTime;
    private static Random random = new Random();
    private int totalTime;
    private int timeStep;
    private int numPatients;
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
        int numNurses = input.nextInt();
        System.out.print("Enter nurse resolution probability (1-100): ");
        this.resolutionChance = input.nextInt();

        this.hospital = new Hospital(this.numPatients, numNurses);
        this.currentTime = 0;

        for (int i = 0; i < numPatients; i++) {
            hospital.addPatient(new Patient());
        }

        for (int i = 0; i < numNurses; i++) {
            hospital.addNurse(new Nurse("Nurse_" + (i + 1), resolutionChance));
        }
    }

    public void run() {
        if (hospital == null || hospital.getPatientCount() == 0) return;
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
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static int getCurrentTime() {
        return currentTime;
    }

    public static int getResolutionChance(){
        return resolutionChance;
    }
}