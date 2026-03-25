import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureMonitorTest {

    @Test
    public void testReadReturnsValidObservation() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();
        Observation obs = monitor.read();

        // Verify we actually got a BloodPressure object back
        assertNotNull(obs, "Monitor should return an observation");
        assertTrue(obs instanceof BloodPressure, "Observation should be an instance of BloodPressure");

        // Verify it has a valid string representation
        assertNotNull(obs.getValueString());
        assertTrue(obs.getValueString().contains("Blood Pressure:"));
    }

    @Test
    public void testObservationHistoryShifting() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();

        // Read 12 times to exceed the array capacity (10)
        Observation firstReading = null;
        for (int i = 0; i < 12; i++) {
            Observation current = monitor.read();
            if (i == 0) {
                firstReading = current;
            }
        }

        // In your implementation, 'observations' is private.
        // We verify that the method executes 12 times without an
        // ArrayIndexOutOfBoundsException, which proves the loop
        // logic (i = length - 1 down to 0) is safe.
        assertNotNull(firstReading);
    }

    @Test
    public void testMultipleUniqueReadings() {
        BloodPressureMonitor monitor = new BloodPressureMonitor();

        Observation read1 = monitor.read();
        Observation read2 = monitor.read();

        // Since BloodPressure is randomized, two consecutive
        // readings are highly unlikely to be the exact same object reference.
        assertNotSame(read1, read2, "Each read() call should create a new Observation object");
    }
}