import java.util.UUID;

public class Patient {

    private UUID id;
    private Device[] patientDevice = new Device[3];

    public Patient(){
        this.id=UUID.randomUUID();
        patientDevice[0]=new HeartRateMonitor();
        //patientDevice[1]=
        //patientDevice[2]=
    }

    public static Patient createPatient() {
        return new Patient();
    }

    public UUID getID(){
        return id;
    }

    public String toCSV() {
        return id.toString();
    }

    @Override
    public String toString() {
        return "Patient ID: " + id.toString();
    }

    public void checkPatient(Hospital hospital){
        for(int i=0; i<patientDevice.length;i++){
            if (patientDevice[i] != null) {
                Observation obs = patientDevice[i].read();
                int severity = obs.checkCondition();

                // If severity is 1 or 2, tell the hospital
                if (severity > 0) {
                    hospital.addAlert(this, severity);
                }
            }
        }

    }

}
