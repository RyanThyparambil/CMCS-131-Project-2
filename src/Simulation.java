import java.util.Scanner;

public class Simulation {
    private Hospital hospital;
    private TelemedicineProvider teleProvider;
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

        System.out.print("Enter max simultaneous telemedicine sessions: ");
        int teleSessions = input.nextInt();

        this.hospital = new Hospital(numPatients, numNurses);
        this.teleProvider = new TelemedicineProvider(teleSessions);
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
            System.out.println("\n--- TIME: " + currentTime + " | Telemed Available: " + teleProvider.getAvailable() + " ---");

            for (int i = 0; i < hospital.getPatientCount(); i++) {
                Patient p = hospital.getPatient(i);
                p.checkPatient(hospital);
                p.pressBuzzer(hospital);
            }

            hospital.dispatchStaff(teleProvider);
            currentTime += timeStep;
        }
    }

    public void process() {
        System.out.println("\n--- FINAL SIMULATION SUMMARY ---");
        MyAlertQueue history = hospital.getHistory();
        int resolvedCount = history.count();
        double totalWait = 0;

        System.out.println("Total Alerts Resolved: " + resolvedCount);

        while (!history.isEmpty()) {
            Alert a = history.dequeue();
            totalWait += a.getResponseTime();
        }

        if (resolvedCount > 0) {
            System.out.println("Average Response Time: " + (totalWait / resolvedCount) + " mins");
            System.out.println("Longest Response Time: " + hospital.getLongestResponseTime() + " mins");
        }

        System.out.println("Simulation Ended Successfully.");
    }

    public static int getRandomInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }

    public static int getCurrentTime() { return currentTime; }
}