import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NurseTest {

    @Test
    public void testNurseState() {
        Nurse nurse = new Nurse("Test Nurse", Simulation.getResolutionChance());
        Patient patient = new Patient();

        assertFalse(nurse.isBusy());
        nurse.assignPatient(patient);
        assertTrue(nurse.isBusy());
    }
}