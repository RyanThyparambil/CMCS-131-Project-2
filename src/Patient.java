import java.util.UUID;

public class Patient {

    private UUID id;
    private Device[] patientDevice = new Device[4];

    public Patient(){
        this.id=UUID.randomUUID();
        patientDevice[0]=new HeartRateMonitor();
        patientDevice[1] = new BloodPressureMonitor();
        patientDevice[2] = new Sp02Monitor();
        patientDevice[3] = new CallBuzzerDevice();
    }

    public Device[] getDevices() {
        return patientDevice;
    }

    public InjuryTier getInjuryTier() {
        return injuryTier;
    }

    private InjuryTier assignRandomInjury() {
        int roll = Simulation.getRandomInt(1, 100);
        if (roll <= 40)
            return InjuryTier.TIER1_MINOR;
        if (roll <= 65)
            return InjuryTier.TIER2_FRACTURE;
        if (roll <= 80)
            return InjuryTier.TIER3_SERIOUS;
        if (roll <= 95)
            return InjuryTier.TIER4_CRITICAL;
        return InjuryTier.TIER5_TBI;
    }

    public static Patient createPatient() {
        return new Patient();
    }

    public UUID getID() {
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
        for (Device device : patientDevice) {
            if (device != null) {
                Observation obs = device.read();
                System.out.println("  [Device] " + obs.getValueString());

                int sev = obs.checkCondition();
                if (sev > 0) {
                    AlertSeverity alertSeverity = mapSeverity(sev);
                    if (alertSeverity != null) {
                        Alert alert = new Alert(this, alertSeverity);
                        hospital.addAlert(alert);
                    }
                }
            }
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

    public void pressBuzzer(Hospital hospital) {
        if (Simulation.getRandomInt(1, 100) <= 5) {
            System.out.println("  [Buzzer] Patient " + id + " pressed the call button!");
            Alert alert = new Alert(this, AlertSeverity.TIER3_EMERGENCY);
            hospital.addAlert(alert);
        }
    }
}
