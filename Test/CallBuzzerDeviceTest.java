import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CallBuzzerDeviceTest {

    @Test
    void testReadReturnsCallBuzzerObservation() {
        CallBuzzerDevice device = new CallBuzzerDevice();
        Observation obs = device.read();

        assertNotNull(obs);
        assertTrue(obs instanceof CallBuzzerObservation);
    }

    @Test
    void testSeverityIsZeroOrThree() {
        CallBuzzerDevice device = new CallBuzzerDevice();
        Observation obs = device.read();

        int severity = obs.checkCondition();
        assertTrue(severity == 0 || severity == 3);
    }

    @Test
    void testRepeatedReadsAreStable() {
        CallBuzzerDevice device = new CallBuzzerDevice();

        for (int i = 0; i < 50; i++) {
            Observation obs = device.read();
            assertNotNull(obs);

            int severity = obs.checkCondition();
            assertTrue(severity == 0 || severity == 3);

            String value = obs.getValueString();
            assertNotNull(value);
            assertTrue(value.contains("Pressed") || value.contains("Not Pressed"));
        }
    }
}
