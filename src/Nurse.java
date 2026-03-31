public class Nurse {
    private String name;
    private Alert currentAlert;
    private int resolutionProbability;
    private boolean usingTelemedicine;

    public Nurse(String name, int resolutionProbability) {
        this.name = name;
        this.resolutionProbability = resolutionProbability;
        this.currentAlert = null;
        this.usingTelemedicine = false;
    }

    public void resolve(Hospital hospital, TelemedicineProvider tele) {
        if (currentAlert == null) {
            currentAlert = hospital.getNextAlert();
            if (currentAlert != null) {
                currentAlert.markResponded();
                AlertSeverity sev = currentAlert.getSeverity();
                if (sev == AlertSeverity.TIER1_NONURGENT || sev == AlertSeverity.TIER2_WARNING) {
                    if (tele.requestSession()) {
                        this.usingTelemedicine = true;
                    }
                }
            }
        }

        if (currentAlert != null) {
            int chance = resolutionProbability;
            if (usingTelemedicine) {
                chance += 15;
            }

            if (Simulation.getRandomInt(1, 100) <= chance) {
                currentAlert.markCompleted();
                hospital.archiveAlert(currentAlert);

                if (usingTelemedicine) {
                    tele.releaseSession();
                    usingTelemedicine = false;
                }

                currentAlert = null;
            }
        }
    }

    public boolean isBusy() {
        return currentAlert != null;
    }
}