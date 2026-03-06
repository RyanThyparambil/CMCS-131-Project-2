import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeartRateTest {

    @Test
    public void testHeartRateBounds() {
        for (int i = 0; i < 100; i++) {
            HeartRate hr = new HeartRate();
            double rate = hr.getRate();
            assertTrue(rate >= 30 && rate <= 220);
        }
    }

    @Test
    public void testCheckCondition() {
        HeartRate hr = new HeartRate();
        int severity = hr.checkCondition();
        assertTrue(severity == 0 || severity == 1 || severity == 2);
    }
}