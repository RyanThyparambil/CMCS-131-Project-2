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

    @Test
    public void testHistoryTracking() {
        Hospital h = new Hospital(1, 1);
        Alert a = new Alert(new Patient(), AlertSeverity.MANUAL);

        h.archiveAlert(a);

        MyAlertQueue history = h.getHistory();
        assertEquals(1, history.count());
        assertEquals(a, history.dequeue());
    }
}