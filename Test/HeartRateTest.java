import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartRateTest {

    @Test
    void testHeartRateValueInRange() {
        HeartRate hr = new HeartRate();
        double value = hr.getRate();


        // Expected physiological range
        assertTrue(value >= 30 && value <= 200);
    }

    @Test
    void testSeverityIsWithinExpectedRange() {
        HeartRate hr = new HeartRate();
        int severity = hr.checkCondition();

        // 0 = normal, 1 = warning, 2 = emergency
        assertTrue(severity >= 0 && severity <= 2);
    }

    @Test
    void testValueStringFormatting() {
        HeartRate hr = new HeartRate();
        String s = hr.getValueString();

        assertNotNull(s);
        assertTrue(s.contains("bpm"));
    }
}
