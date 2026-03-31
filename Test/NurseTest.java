import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NurseTest {
    @Test
    public void testResolutionLoop() {
        Hospital hospital = new Hospital(1, 1);
        hospital.receiveAlert(new Alert(new Patient(), AlertSeverity.TIER3_EMERGENCY));
        hospital.receiveAlert(new Alert(new Patient(), AlertSeverity.TIER3_EMERGENCY));

        TelemedicineProvider tele = new TelemedicineProvider(0);
        Nurse nurse = new Nurse("FastNurse", 100);

        nurse.resolve(hospital, tele);

        assertFalse(nurse.isBusy());
        assertEquals(0, hospital.getUrgentCount());
        assertEquals(2, hospital.getHistory().count());
    }

    @Test
    public void testTelemedicineUsage() {
        Hospital hospital = new Hospital(1, 1);
        hospital.receiveAlert(new Alert(new Patient(), AlertSeverity.TIER1_NONURGENT));

        TelemedicineProvider tele = new TelemedicineProvider(1);
        Nurse nurse = new Nurse("TeleNurse", 0);

        nurse.resolve(hospital, tele);

        assertEquals(0, tele.getAvailable());
    }
}