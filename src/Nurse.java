public class Nurse {
    private String name;
    private boolean isBusy;
    private Patient currentTask;
    private int resolutionProbability;

    public Nurse(String name, int resolutionProbability) {
        this.name = name;
        this.isBusy = false;
        this.currentTask = null;
        this.resolutionProbability = resolutionProbability;
    }

    public void treatPatient() {
        if (currentTask != null) {
            if (Simulation.getRandomInt(1, 100) <= resolutionProbability) {
                System.out.println("Nurse " + name + " resolved alert for Patient: " + currentTask.getID());
                currentTask = null;
                isBusy = false;
            } else {
                System.out.println("Nurse " + name + " is still working on Patient: " + currentTask.getID());
            }
        }
    }

    public void assignPatient(Patient p) {
        this.currentTask = p;
        this.isBusy = true;
        System.out.println("Nurse " + name + " assigned to Patient: " + p.getID());
    }

    public boolean isBusy() {
        return isBusy;
    }
}