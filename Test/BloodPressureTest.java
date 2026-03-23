import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureTest {

    @Test
    void testBloodPressureValuesInRange() {
        BloodPressure bp = new BloodPressure();

        int sys = bp.getSystolic();
        int dia = bp.getDiastolic();

        assertTrue(sys >= 70 && sys <= 200);
        assertTrue(dia >= 40 && dia <= 130);
    }

    @Test
    void testSeverityIsWithinExpectedRange() {
        BloodPressure bp = new BloodPressure();
        int severity = bp.checkCondition();

        assertTrue(severity >= 0 && severity <= 2);
    }

    @Test
    void testValueStringFormatting() {
        BloodPressure bp = new BloodPressure();
        String s = bp.getValueString();

        assertNotNull(s);
        assertTrue(s.contains("/"));
        assertTrue(s.contains("mmHg"));
    }
}
