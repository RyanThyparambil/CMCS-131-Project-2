import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertTest {
    @Test
    public void testAlertStatus() {
        Patient p = new Patient();
        Alert a = new Alert(p, AlertSeverity.MANUAL);

        assertEquals(-1, a.getTimeResponded());
        a.markResponded();
        assertTrue(a.getTimeResponded() >= 0);

        assertNotNull(a.getDescription());
    }
}