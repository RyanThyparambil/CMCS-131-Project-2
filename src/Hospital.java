public class Hospital {
    private Patient[] patients;
    private Patient[] highSeverityAlerts = new Patient[100];
    private Patient[] lowSeverityAlerts = new Patient[100];
    private Patient[] manualBuzzerCalls = new Patient[100];

    private Nurse[] nurses;
    private int nurseCount;
    private int patientCount;

    private int highAlertCount = 0;
    private int lowAlertCount = 0;
    private int manualCallCount = 0;

    public Hospital(int maxPatients, int maxNurses) {
        this.patients = new Patient[maxPatients];
        this.nurses = new Nurse[maxNurses];
        this.patientCount = 0;
        this.nurseCount = 0;
    }

    public boolean addPatient(Patient p) {
        if (patientCount < patients.length) {
            patients[patientCount] = p;
            patientCount++;
            return true;
        }
        return false;
    }

    public void addNurse(Nurse n) {
        if (nurseCount < nurses.length) {
            nurses[nurseCount] = n;
            nurseCount++;
        }
    }

    public void addAlert(Patient pat, int severity) {
        if (severity == 1 && lowAlertCount < lowSeverityAlerts.length) {
            lowSeverityAlerts[lowAlertCount] = pat;
            lowAlertCount++;
            System.out.println("ALERT: Low severity for " + pat.getID());
        } else if (severity == 2 && highAlertCount < highSeverityAlerts.length) {
            highSeverityAlerts[highAlertCount] = pat;
            highAlertCount++;
            System.out.println("URGENT: High severity for " + pat.getID());
        }
    }

    public void addManualCall(Patient pat) {
        if (manualCallCount < manualBuzzerCalls.length) {
            manualBuzzerCalls[manualCallCount] = pat;
            manualCallCount++;
        }
    }

    public void dispatchStaff() {
        for (int i = 0; i < nurseCount; i++) {
            Nurse n = nurses[i];

            if (!n.isBusy()) {
                if (highAlertCount > 0) {
                    n.assignPatient(highSeverityAlerts[highAlertCount - 1]);
                    highAlertCount--;
                }
                else if (manualCallCount > 0) {
                    n.assignPatient(manualBuzzerCalls[manualCallCount - 1]);
                    manualCallCount--;
                    System.out.println("Nurse responding to manual buzzer call.");
                }
                else if (lowAlertCount > 0) {
                    n.assignPatient(lowSeverityAlerts[lowAlertCount - 1]);
                    lowAlertCount--;
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
    public int getHighAlertCount() { return highAlertCount; }
    public int getLowAlertCount() { return lowAlertCount; }
    public int getManualCallCount() { return manualCallCount; }
}