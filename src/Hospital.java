public class Hospital {
    private Patient[] patients;
    private Nurse[] nurses;
    private MyAlertQueue highPriorityQueue;
    private MyAlertQueue lowPriorityQueue;
    private int nurseCount;
    private int patientCount;
    private int totalHighAlerts;
    private int totalLowAlerts;

    public Hospital(int maxPatients, int maxNurses) {
        this.patients = new Patient[maxPatients];
        this.nurses = new Nurse[maxNurses];
        this.highPriorityQueue = new MyAlertQueue(100);
        this.lowPriorityQueue = new MyAlertQueue(100);
        this.patientCount = 0;
        this.nurseCount = 0;
        this.totalHighAlerts = 0;
        this.totalLowAlerts = 0;
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
        Alert newAlert = new Alert(pat, severity);
        if (severity == 2) {
            highPriorityQueue.enqueue(newAlert);
            totalHighAlerts++;
            System.out.println("URGENT: High priority alert added. Queue size: " + highPriorityQueue.getSize());
        } else {
            lowPriorityQueue.enqueue(newAlert);
            totalLowAlerts++;
            System.out.println("ALERT: Low priority alert added. Queue size: " + lowPriorityQueue.getSize());
        }
    }

    public void addManualCall(Patient pat) {
        Alert buzzerAlert = new Alert(pat, 2);
        highPriorityQueue.enqueue(buzzerAlert);
        System.out.println("BUZZER: Manual call added to high priority queue.");
    }

    public void dispatchStaff() {
        for (int i = 0; i < nurseCount; i++) {
            Nurse n = nurses[i];
            if (!n.isBusy()) {
                Alert next = highPriorityQueue.dequeue();
                if (next == null) {
                    next = lowPriorityQueue.dequeue();
                }

                if (next != null) {
                    n.assignPatient(next.getPatient());
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
    public int getHighAlertCount() { return totalHighAlerts; }
    public int getLowAlertCount() { return totalLowAlerts; }
}