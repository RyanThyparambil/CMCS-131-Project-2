import java.util.Scanner;

public class Simulation {
    private Hospital hospital;
    private static int currentTime;
    private int totalTime;
    private int timeStep;
    private static int resolutionChance;

    public void setup() {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Hospital Simulation Setup ---");
        System.out.print("Enter total simulation time (mins): ");
        this.totalTime = input.nextInt();
        System.out.print("Enter time step (mins): ");
        this.timeStep = input.nextInt();
        System.out.print("Enter number of patients: ");
        int numPatients = input.nextInt();
        System.out.print("Enter number of nurses: ");
        int numNurses = input.nextInt();
        System.out.print("Enter nurse resolution probability (1-100): ");
        resolutionChance = input.nextInt();

        this.hospital = new Hospital(numPatients, numNurses);
        currentTime = 0;

        for (int i = 0; i < numPatients; i++) {
            hospital.addPatient(new Patient());
        }

        for (int i = 0; i < numNurses; i++) {
            hospital.addNurse(new Nurse("Nurse_" + (i + 1), resolutionChance));
        }
    }

    public void run() {
        while (currentTime < totalTime) {
            System.out.println("\nTIME: " + currentTime);
            System.out.println("Queue Status: Urgent(" + hospital.getUrgentCount() + ") Non-Urgent(" + hospital.getNonUrgentCount() + ")");

            for (int i = 0; i < hospital.getPatientCount(); i++) {
                Patient p = hospital.getPatient(i);
                p.checkPatient(hospital);
                p.pressBuzzer(hospital);
            }

            hospital.dispatchStaff();
            currentTime += timeStep;
        }
    }

    public void process() {
        System.out.println("\n--- Simulation Complete ---");
    }

    public static int getRandomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static int getCurrentTime() { return currentTime; }
    public static int getResolutionChance() { return resolutionChance; }
}