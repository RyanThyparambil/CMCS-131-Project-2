public class Hospital {

    private Patient[] patients;
    private Nurse[] nurses;
    private int nurseCount;
    private int patientCount;

    //private AlertQueueManager alertManager = new AlertQueueManager();

    private int totalHighAlerts;
    private int totalLowAlerts;
    private int totalManualAlerts;

    public Hospital(int maxPatients, int maxNurses) {
        this.patients = new Patient[maxPatients];
        this.nurses = new Nurse[maxNurses];
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

    public void addAlert(Patient pat, int severity) {

        System.out.println("ALERT CREATED at t=" + alert.getTimeCreated() +
                " | Severity: " + alert.getSeverity());

        if (alert.getSeverity() == AlertSeverity.MANUAL) {
            alertManager.addManual(alert);
            totalManualAlerts++;
            return;
        }

        alertManager.addAlert(alert);

        if (alert.getSeverity() == AlertSeverity.TIER3_EMERGENCY) {
            totalHighAlerts++;
        } else {
            totalLowAlerts++;
        }
    }

    public void dispatchStaff() {
        for (int i = 0; i < nurseCount; i++) {
            Nurse n = nurses[i];

            if (!n.isBusy()) {

                Alert next = alertManager.popTier3();
                if (next == null) next = alertManager.popTier2();
                if (next == null) next = alertManager.popTier1();
                if (next == null) next = alertManager.popManual();

                if (next != null) {
                    n.assignAlert(next);
                }
            }

            n.treatPatient();
        }
    }

    public Patient getPatient(int index) {
        if (index >= 0 && index < patientCount) return patients[index];
        return null;
    }

    public int getPatientCount() { return patientCount; }
    public int getNurseCount() { return nurseCount; }

    public int getHighAlertCount() { return totalHighAlerts; }
    public int getLowAlertCount() { return totalLowAlerts; }
    public int getManualAlertCount() { return totalManualAlerts; }
}