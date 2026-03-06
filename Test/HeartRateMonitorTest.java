import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartRateMonitorTest {

    @Test
    public void testReadAndStore() {
        HeartRateMonitor monitor = new HeartRateMonitor();
        Observation result = monitor.read();
        assertNotNull(result);
        assertTrue(result instanceof HeartRate);
    }

    @Test
    public void testArrayCapacity() {
        HeartRateMonitor monitor = new HeartRateMonitor();
        for (int i = 0; i < 15; i++) {
            assertNotNull(monitor.read());
        }
    }
}