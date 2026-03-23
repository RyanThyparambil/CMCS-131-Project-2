import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartRateMonitorTest {

    @Test
    void testReadReturnsHeartRateObservation() {
        HeartRateMonitor monitor = new HeartRateMonitor();
        Observation obs = monitor.read();

        assertNotNull(obs);
        assertTrue(obs instanceof HeartRate);
    }

    @Test
    void testHeartRateValueWithinExpectedRange() {
        HeartRateMonitor monitor = new HeartRateMonitor();
        HeartRate hr = (HeartRate) monitor.read();

        double value = hr.getRate();

        // Heart rate should be between 30 and 200 bpm
        assertTrue(value >= 30 && value <= 200);
    }

    @Test
    void testMonitorDoesNotReturnNull() {
        HeartRateMonitor monitor = new HeartRateMonitor();
        assertNotNull(monitor.read());
    }
}
