import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NurseTest {
    @Test
    public void testResolutionLoop() {
        Hospital hospital = new Hospital(1, 1);
        hospital.receiveAlert(new Alert(new Patient(), AlertSeverity.TIER3_EMERGENCY));
        hospital.receiveAlert(new Alert(new Patient(), AlertSeverity.TIER3_EMERGENCY));

        Nurse nurse = new Nurse("FastNurse", 100);
        nurse.resolve(hospital);

        assertFalse(nurse.isBusy());
        assertEquals(0, hospital.getUrgentCount());
    }
}