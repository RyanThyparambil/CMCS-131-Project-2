public class Hospital {
    private Patient[] patients;
    private Nurse[] nurses;
    private int nurseCount;
    private int patientCount;
    private AlertQueueManager alertManager;

    private int totalHighAlerts = 0;
    private int totalLowAlerts = 0;
    private int totalManualAlerts = 0;

    public Hospital(int maxPatients, int maxNurses) {
        this.patients = new Patient[maxPatients];
        this.nurses = new Nurse[maxNurses];
        this.alertManager = new AlertQueueManager();
        this.patientCount = 0;
        this.nurseCount = 0;
    }

    public boolean addPatient(Patient p) {
        if (patientCount < patients.length) {
            patients[patientCount++] = p;
            return true;
        }
        return false;
    }

    public void addNurse(Nurse n) {
        if (nurseCount < nurses.length) {
            nurses[nurseCount++] = n;
        }
    }

    public void receiveAlert(Alert alert) {
        alertManager.addAlert(alert);

        if (alert.getSeverity() == AlertSeverity.TIER3_EMERGENCY) totalHighAlerts++;
        else if (alert.getSeverity() == AlertSeverity.MANUAL) totalManualAlerts++;
        else totalLowAlerts++;

        System.out.println("ALERT RECEIVED: " + alert.getSeverity() + " | Total in Queue: " + alertManager.getTotalCount());
    }

    public Alert getNextAlert() {
        return alertManager.getNextAlert();
    }

    public void dispatchStaff() {
        for (int i = 0; i < nurseCount; i++) {
            nurses[i].resolve(this);
        }
    }

    public int getUrgentCount() {
        return alertManager.getUrgentCount();
    }

    public int getNonUrgentCount() {
        return alertManager.getNonUrgentCount();
    }

    public int getPatientCount() { return patientCount; }
    public Patient getPatient(int index) { return patients[index]; }
    public int getHighAlertCount() { return totalHighAlerts; }
    public int getLowAlertCount() { return totalLowAlerts; }
    public int getManualAlertCount() { return totalManualAlerts; }
}