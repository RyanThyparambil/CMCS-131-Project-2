import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureMonitorTest {

    @Test
    void testReadReturnsBloodPressureObservation() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();
        Observation obs = monitor.read();

        assertNotNull(obs);
        assertTrue(obs instanceof BloodPressure);
    }

    @Test
    void testBloodPressureValuesWithinExpectedRange() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();
        BloodPressure bp = (BloodPressure) monitor.read();

        int systolic = bp.getSystolic();
        int diastolic = bp.getDiastolic();

        // Reasonable physiological ranges
        assertTrue(systolic >= 70 && systolic <= 200);
        assertTrue(diastolic >= 40 && diastolic <= 130);
    }

    @Test
    void testMonitorDoesNotReturnNull() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();
        assertNotNull(monitor.read());
    }
}
