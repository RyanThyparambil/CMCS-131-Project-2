import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartRateTest {

    @Test
    public void testHeartRateInitialization() {
        HeartRate hr = new HeartRate();

        // Verify the rate is a positive, realistic number
        assertTrue(hr.getRate() > 0, "Heart rate should be a positive value");

        // Verify getValueString contains the correct label and value
        String display = hr.getValueString();
        assertTrue(display.contains("Heart Rate:"), "Display string should contain the correct label");
        assertTrue(display.contains(String.valueOf(hr.getRate())), "Display string should include the actual rate");
    }

    @Test
    public void testCheckConditionLogic() {
        // Because the constructor is randomized, we test the logic
        // against the value generated for this specific instance.
        HeartRate hr = new HeartRate();
        double rate = hr.getRate();
        int severity = hr.checkCondition();

        // Logic check based on the HeartRate class implementation:
        // Tier 2: > 150 or < 40
        if (rate > 150 || rate < 40) {
            assertEquals(2, severity, "Extreme heart rates should be Tier 2");
        }
        // Tier 1: > 110 or < 55
        else if (rate > 110 || rate < 55) {
            assertEquals(1, severity, "Tachycardia/Bradycardia should be Tier 1");
        }
        // Tier 0: Normal
        else {
            assertEquals(0, severity, "Normal heart rate should be Tier 0");
        }
    }

    @Test
    public void testInheritedTime() {
        // Verify that the super() call in the constructor worked
        HeartRate hr = new HeartRate();
        // Since Simulation.currentTime is likely 0 at start of tests
        assertTrue(hr.getTime() >= 0, "Observation time should be initialized");
    }
}