public class Nurse {

    private String name;
    private boolean isBusy;

    private Patient currentTask;
    private Alert currentAlert;

    public Nurse(String name) {
        this.name = name;
        this.isBusy = false;
        this.currentTask = null;
        this.currentAlert = null;
    }

    public Patient getCurrentPatient() {
        return this.currentTask;
    }

    public boolean isBusy() {
        return isBusy;
    }

    /**
     * Compatibility method so NurseTest continues to work.
     * Creates a temporary Tier 1 alert and assigns it.
     */
    public void assignPatient(Patient p) {
        Alert temp = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        assignAlert(temp);
    }

    public void assignAlert(Alert alert) {
        this.currentAlert = alert;
        this.currentTask = alert.getPatient();
        this.isBusy = true;

        alert.markResponded();

        System.out.println("Nurse " + name +
                " RESPONDED at t=" + alert.getTimeResponded() +
                " to Patient " + currentTask.getID());
    }

    public void treatPatient() {
        if (currentAlert != null) {

            int chance = Simulation.getResolutionChance();

            if (Simulation.getRandomInt(1, 100) <= chance) {

                currentAlert.markCompleted();

                System.out.println("Nurse " + name +
                        " COMPLETED alert at t=" + currentAlert.getTimeCompleted() +
                        " for Patient " + currentTask.getID());

                currentAlert = null;
                currentTask = null;
                isBusy = false;

            } else {
                System.out.println("Nurse " + name +
                        " is still working on Patient " + currentTask.getID());
            }
        }
    }

    public boolean hasPatient(Patient p) {
        return currentTask == p;
    }
}
