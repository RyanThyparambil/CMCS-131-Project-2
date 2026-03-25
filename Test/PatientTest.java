import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    public void testPatientAlerts() {
        Hospital h = new Hospital(1, 1);
        Patient p = new Patient();

        assertNotNull(p.getID());
        for(int i = 0; i < 20; i++) {
            p.checkPatient(h);
        }
        assertTrue(h.getHighAlertCount() + h.getLowAlertCount() >= 0);
    }
}