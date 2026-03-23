import java.util.UUID;

public class Patient {

    private UUID id;
    private Device[] patientDevice = new Device[3];

    public Patient(){
        this.id=UUID.randomUUID();
        patientDevice[0]=new HeartRateMonitor();
        patientDevice[1] = new BloodPressureMonitor();
        patientDevice[2] = new Sp02Monitor();
        patientDevice[3] = new CallBuzzerDevice();
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

    public void checkPatient(Hospital hospital) {
        for (int i = 0; i < patientDevice.length; i++) {
            if (patientDevice[i] != null) {
                Observation obs = patientDevice[i].read();

                // Print the reading so you can see it in the console
                System.out.println("  [Device] " + obs.getValueString());

                int severity = obs.checkCondition();
                if (severity > 0) {
                    hospital.addAlert(this, severity);
                }
            }
        }
    }

    public void pressBuzzer(Hospital hospital) {
        if (Simulation.getRandomInt(1, 100) <= 5) {
            System.out.println("  [Buzzer] Patient " + id + " pressed the call button!");
            hospital.addManualCall(this);
        }
    }

}
