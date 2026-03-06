public class Hospital {
    private Patient [] patients;
    private Patient[] highSeverityAlerts = new Patient[100];
    private Patient[] lowSeverityAlerts = new Patient[100];
    private Nurse[] nurses;
    private int nurseCount;
    //private Nurse[] nurses;

    private int patientCount;
    // private int nurseCount;

    public Hospital(int maxPatients, int maxNurses) {
        patients = new Patient[maxPatients];
        //nurses = new Nurse[maxPatients];
        patientCount = 0;
        //nurseCount = 0;
        this.nurses = new Nurse[maxNurses];
        this.nurseCount = 0;

    }

    public boolean addPatient(Patient p){
        if(patientCount < patients.length){
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
    public Patient[] getPatients() {
        return patients;
    }
    public int getPatientCount() {
        return patientCount;
    }
    public Patient getPatient(int index){
        if(index >= 0 && index < patientCount){
            return patients[index];
        }
        return null;
    }
    public void displayPatients(){
        for(int i = 0; i < patients.length; i++){
            System.out.println(patients[i]);
        }
    }

    private int highAlertCount = 0;
    private int lowAlertCount = 0;

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

    public int getHighAlertCount(){
        return highAlertCount;
    }
    public int getLowAlertCount(){
        return lowAlertCount;
    }

    //public boolean addNurse(Nurse n) {
        //if (nurseCount < nurses.length) {
            //nurses[nurseCount] = n;
            //nurseCount++;
           // return true;
        //}
        //return false; // nurse capacity full
    //}

    //public Nurse[] getNurses() {
        //return nurses;
    //}

    //public int getNurseCount() {
        //return nurseCount;
    //}

    //public Nurse getNurse(int index) {
        //if (index >= 0 && index < nurseCount) {
            //return nurses[index];
        //}
        //return null;
    //}

    //public void displayNurses() {
        //for (int i = 0; i < nurseCount; i++) {
            //System.out.println(nurses[i]);
        //}
    //}

    public void dispatchStaff() {
        for (int i = 0; i < nurseCount; i++) {
            Nurse n = nurses[i];

            if (!n.isBusy()) {
                if (highAlertCount > 0) {
                    n.assignPatient(highSeverityAlerts[highAlertCount - 1]);
                    highAlertCount--; // Remove from "queue"
                }

                else if (lowAlertCount > 0) {
                    n.assignPatient(lowSeverityAlerts[lowAlertCount - 1]);
                    lowAlertCount--;
                }
            }

                       n.treatPatient();
        }
    }
}
