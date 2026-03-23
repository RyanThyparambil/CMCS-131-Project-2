import org.junit.Test;
import static org.junit.Assert.*;

public class NurseTest {

    @Test
    public void testAssignPatient() {
        Nurse nurse = new Nurse("Alice");
        Patient p = new Patient();

        // Uses compatibility wrapper in Nurse
        nurse.assignPatient(p);

        assertTrue(nurse.isBusy());
        assertEquals(p, nurse.getCurrentPatient());
    }

    @Test
    public void testTreatPatientSuccess() {
        // Force 100% success
        setResolutionChanceForTest(100);

        Nurse nurse = new Nurse("Bob");
        Patient p = new Patient();

        // Uses compatibility wrapper
        nurse.assignPatient(p);

        nurse.treatPatient();

        assertFalse(nurse.isBusy());
        assertNull(nurse.getCurrentPatient());
    }

    @Test
    public void testTreatPatientFailure() {
        // Force 0% success
        setResolutionChanceForTest(0);

        Nurse nurse = new Nurse("Carol");
        Patient p = new Patient();

        // Uses compatibility wrapper
        nurse.assignPatient(p);

        nurse.treatPatient();

        assertTrue(nurse.isBusy());
        assertEquals(p, nurse.getCurrentPatient());
    }

    // Helper to modify Simulation.resolutionChance for testing
    private void setResolutionChanceForTest(int chance) {
        try {
            java.lang.reflect.Field field = Simulation.class.getDeclaredField("resolutionChance");
            field.setAccessible(true);
            field.setInt(null, chance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
