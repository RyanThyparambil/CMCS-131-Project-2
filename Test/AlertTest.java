import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertTest {

    @Test
    void testAlertStoresPatientAndSeverity() {
        Patient p = new Patient();
        Alert alert = new Alert(p, AlertSeverity.TIER2_WARNING);

        assertEquals(p, alert.getPatient());
        assertEquals(AlertSeverity.TIER2_WARNING, alert.getSeverity());
    }

    @Test
    void testAlertNotNullFields() {
        Patient p = new Patient();
        Alert alert = new Alert(p, AlertSeverity.TIER1_NONURGENT);

        assertNotNull(alert.getPatient());
        assertNotNull(alert.getSeverity());
    }
}
