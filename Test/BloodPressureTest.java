import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureTest {

    @Test
    public void testInitialization() {
        BloodPressure bp = new BloodPressure();

        // Verify that systolic and diastolic are within physically possible ranges
        // Gaussian distribution centered at 120/80 should stay within these bounds usually
        assertTrue(bp.getSystolic() > 0, "Systolic should be positive");
        assertTrue(bp.getDiastolic() > 0, "Diastolic should be positive");

        // Verify the string contains the "/" separator
        String output = bp.getValueString();
        assertTrue(output.contains("Blood Pressure:"), "Output string should label the data");
        assertTrue(output.contains("/"), "Output should format as Systolic/Diastolic");
    }

    @Test
    public void testCheckConditionLogic() {
        // Since we can't "set" the values manually in your provided class,
        // we test the consistency of the current random instance.
        BloodPressure bp = new BloodPressure();
        int s = bp.getSystolic();
        int d = bp.getDiastolic();
        int severity = bp.checkCondition();

        // Tier 2: Emergency (Systolic > 180 or Diastolic > 120)
        if (s > 180 || d > 120) {
            assertEquals(2, severity, "High BP should trigger Tier 2");
        }
        // Tier 1: Warning (Systolic > 140 or Diastolic > 90)
        else if (s > 140 || d > 90) {
            assertEquals(1, severity, "Elevated BP should trigger Tier 1");
        }
        // Tier 0: Normal
        else {
            assertEquals(0, severity, "Normal BP should trigger Tier 0");
        }
    }

    @Test
    public void testTimeStamping() {
        // Ensure the observation captures the simulation time upon creation
        BloodPressure bp = new BloodPressure();
        assertEquals(Simulation.getCurrentTime(), bp.getTime(),
                "Observation should record the simulation time it was created");
    }
}