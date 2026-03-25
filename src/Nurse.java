public class Nurse {
    private String name;
    private Alert currentAlert;
    private int resolutionProbability;

    public Nurse(String name, int resolutionProbability) {
        this.name = name;
        this.resolutionProbability = resolutionProbability;
        this.currentAlert = null;
    }

    public void resolve(Hospital hospital) {
        if (currentAlert == null) {
            currentAlert = hospital.getNextAlert();
            if (currentAlert != null) {
                currentAlert.markResponded();
            }
        }

        while (currentAlert != null) {
            if (Simulation.getRandomInt(1, 100) <= resolutionProbability) {
                currentAlert.markCompleted();
                System.out.println("Nurse " + name + " SUCCESS: " + currentAlert.toString());

                currentAlert = hospital.getNextAlert();
                if (currentAlert != null) {
                    currentAlert.markResponded();
                }
            } else {
                System.out.println("Nurse " + name + " BUSY: Still working on alert for Patient " + currentAlert.getPatient().getID());
                break;
            }
        }
    }

    public boolean isBusy() {
        return currentAlert != null;
    }
}