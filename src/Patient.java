import java.util.UUID;

public class Patient {
    private UUID id;
    private Device[] patientDevice = new Device[4];

    public Patient() {
        this.id = UUID.randomUUID();
        patientDevice[0] = new HeartRateMonitor();
        patientDevice[1] = new BloodPressureMonitor();
        patientDevice[2] = new Sp02Monitor();
        patientDevice[3] = new CallBuzzerDevice();
    }

    public void checkPatient(Hospital hospital) {
        for (Device device : patientDevice) {
            if (device != null) {
                Observation obs = device.read();
                int sev = obs.checkCondition();
                if (sev > 0) {
                    AlertSeverity alertSeverity = mapSeverity(sev);
                    if (alertSeverity != null) {
                        hospital.receiveAlert(new Alert(this, alertSeverity));
                    }
                }
            }
        }
    }

    public void pressBuzzer(Hospital hospital) {
        if (Simulation.getRandomInt(1, 100) <= 5) {
            System.out.println("  [Buzzer] Patient " + id + " pressed the call button!");
            hospital.receiveAlert(new Alert(this, AlertSeverity.MANUAL));
        }
    }

    private AlertSeverity mapSeverity(int sev) {
        return switch (sev) {
            case 1 -> AlertSeverity.TIER1_NONURGENT;
            case 2 -> AlertSeverity.TIER2_WARNING;
            case 3 -> AlertSeverity.TIER3_EMERGENCY;
            default -> null;
        };
    }

    public UUID getID() { return id; }
}