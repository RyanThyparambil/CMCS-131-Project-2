import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObservationTest {

    @Test
    void testHeartRateSeverityRange() {
        HeartRate hr = new HeartRate();
        int sev = hr.checkCondition();
        assertTrue(sev >= 0 && sev <= 2);
    }

    @Test
    void testBloodPressureSeverityRange() {
        BloodPressure bp = new BloodPressure();
        int sev = bp.checkCondition();
        assertTrue(sev >= 0 && sev <= 2);
    }

    @Test
    void testSp02SeverityRange() {
        Sp02 sp = new Sp02();
        int sev = sp.checkCondition();
        assertTrue(sev >= 0 && sev <= 2);
    }
}
