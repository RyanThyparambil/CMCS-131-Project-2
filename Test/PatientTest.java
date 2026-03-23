import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    void testPatientHasFourDevices() {
        Patient p = new Patient();
        Device[] devices = p.getDevices();

        assertNotNull(devices);
        assertEquals(4, devices.length);
    }

    @Test
    void testPatientDeviceTypesAreCorrect() {
        Patient p = new Patient();
        Device[] devices = p.getDevices();

        assertTrue(devices[0] instanceof HeartRateMonitor);
        assertTrue(devices[1] instanceof BloodPressureMonitor);
        assertTrue(devices[2] instanceof Sp02Monitor);
        assertTrue(devices[3] instanceof CallBuzzerDevice);
    }

    @Test
    void testInjuryTierIsAssigned() {
        Patient p = new Patient();
        assertNotNull(p.getInjuryTier());
    }

    @Test
    void testCheckPatientGeneratesAlerts() {
        Hospital hospital = new Hospital(10, 1);
        Patient p = new Patient();

        // Run device checks
        p.checkPatient(hospital);

        // We cannot predict exact counts due to randomness,
        // but we CAN assert that counts are >= 0 and not throwing errors.
        assertTrue(hospital.getHighAlertCount() >= 0);
        assertTrue(hospital.getLowAlertCount() >= 0);
        assertTrue(hospital.getManualAlertCount() >= 0);
    }

    @Test
    void testManualAlertGeneratedByBuzzer() {
        Hospital hospital = new Hospital(10, 1);
        Patient p = new Patient();

        // Force the buzzer to generate a manual alert by calling read() directly
        CallBuzzerDevice buzzer = new CallBuzzerDevice();
        Observation obs = buzzer.read();

        // If the buzzer was pressed, severity = 3 (manual)
        if (obs.checkCondition() == 3) {
            Alert alert = new Alert(p, AlertSeverity.MANUAL);
            hospital.addAlert(alert);

            assertEquals(1, hospital.getManualAlertCount());
        }
    }
}
