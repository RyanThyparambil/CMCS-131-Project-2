import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {
    @Test
    public void testHospitalFlow() {
        Hospital h = new Hospital(5, 2);
        Patient p = new Patient();
        h.addPatient(p);

        Alert a = new Alert(p, AlertSeverity.TIER3_EMERGENCY);
        h.receiveAlert(a);

        assertEquals(1, h.getHighAlertCount());
        assertNotNull(h.getNextAlert());
    }
}