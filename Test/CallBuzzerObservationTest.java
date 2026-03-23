import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CallBuzzerObservationTest {

    @Test
    void testSeverityIsZeroOrThree() {
        CallBuzzerObservation obs = new CallBuzzerObservation();
        int severity = obs.checkCondition();

        assertTrue(severity == 0 || severity == 3);
    }

    @Test
    void testValueStringIsValid() {
        CallBuzzerObservation obs = new CallBuzzerObservation();
        String s = obs.getValueString();

        assertNotNull(s);
        assertTrue(s.contains("Pressed") || s.contains("Not Pressed"));
    }

    @Test
    void testObservationNeverCrashes() {
        // Run multiple times to ensure stability
        for (int i = 0; i < 50; i++) {
            CallBuzzerObservation obs = new CallBuzzerObservation();
            assertNotNull(obs.getValueString());
            int sev = obs.checkCondition();
            assertTrue(sev == 0 || sev == 3);
        }
    }
}
