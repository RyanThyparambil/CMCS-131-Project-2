
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BloodPressureTest {
    @Test
    public void testBPLogic() {
        BloodPressure bp = new BloodPressure();
        assertTrue(bp.getSystolic() > bp.getDiastolic());
    }
}
