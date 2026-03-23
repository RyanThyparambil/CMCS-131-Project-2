import org.junit.Test;
import static org.junit.Assert.*;

public class HospitalTest {

    @Test
    public void testAddPatient() {
        Hospital hospital = new Hospital(0, 0);
        Patient p = new Patient();

        hospital.addPatient(p);

        assertEquals(1, hospital.getPatientCount());
        assertEquals(p, hospital.getPatient(0));
    }

    @Test
    public void testAddNurse() {
        Hospital hospital = new Hospital(0, 0);
        Nurse n = new Nurse("Alice");

        hospital.addNurse(n);

        assertEquals(1, hospital.getNurseCount());
    }

    @Test
    public void testDispatchAssignsNurse() {
        // Set resolution chance high so nurse resolves quickly
        setResolutionChanceForTest(100);

        Hospital hospital = new Hospital(0, 0);
        Patient p = new Patient();
        Nurse n = new Nurse("Bob");

        hospital.addPatient(p);
        hospital.addNurse(n);

        // Simulate an alert
        hospital.dispatchStaff();

        assertTrue(n.isBusy());
        assertEquals(p, n.getCurrentPatient());
    }

    @Test
    public void testDispatchResolvesAlert() {
        // Force guaranteed resolution
        setResolutionChanceForTest(100);

        Hospital hospital = new Hospital(0, 0);
        Patient p = new Patient();
        Nurse n = new Nurse("Carol");

        hospital.addPatient(p);
        hospital.addNurse(n);

        // Assign
        hospital.dispatchStaff();
        assertTrue(n.isBusy());

        // Resolve
        n.treatPatient();

        assertFalse(n.isBusy());
        assertNull(n.getCurrentPatient());
    }

    // Helper to modify Simulation's static resolutionChance for testing
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
