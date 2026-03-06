public class Nurse {
    private String name;
    private boolean isBusy;
    private Patient currentTask;

    public Nurse(String name) {
        this.name = name;
        this.isBusy = false;
        this.currentTask = null;
    }
    public void treatPatient() {
        if (currentTask != null) {
            // 80% chance to resolve the alert
            if (Simulation.getRandomInt(1, 10) <= 8) {
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
    }

    public boolean isBusy() {
        return isBusy;
    }
}