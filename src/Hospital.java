public class Hospital {
    private Patient [] patients;

    //private Nurse[] nurses;

    private int patientCount;
    // private int nurseCount;

    public Hospital(int maxPatients) {
        patients = new Patient[maxPatients];
        //nurses = new Nurse[maxPatients];
        patientCount = 0;
        //nurseCount = 0;

    }

    public boolean addPatient(Patient p){
        if(patientCount < patients.length){
            patients[patientCount] = p;
            patientCount++;
            return true;
        }
        return false;
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
}
