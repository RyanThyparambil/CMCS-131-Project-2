import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HospitalTest {

    @Test
    void testAddPatient() {
        Hospital hospital = new Hospital(2, 2);
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        assertTrue(hospital.addPatient(patient1));
        assertTrue(hospital.addPatient(patient2));
        assertEquals(2, hospital.getPatientCount());
        assertEquals(patient1, hospital.getPatient(0));
        assertEquals(patient2, hospital.getPatient(1));
    }

    @Test
    void testAddPatientFailsWhenFull() {
        Hospital hospital = new Hospital(2, 2);
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        Patient patient3 = new Patient();

        hospital.addPatient(patient1);
        hospital.addPatient(patient2);

        assertFalse(hospital.addPatient(patient3));
        assertEquals(2, hospital.getPatientCount());
    }

    @Test
    void testGetPatientInvalidIndex() {
        Hospital hospital = new Hospital(2, 2);
        Patient patient1 = new Patient();
        hospital.addPatient(patient1);

        assertNull(hospital.getPatient(-1));
        assertNull(hospital.getPatient(5));
    }

    @Test
    void testAlertAndBuzzerCounters() {
        Hospital hospital = new Hospital(2, 2);
        Patient p = new Patient();

        hospital.addAlert(p, 2);
        hospital.addAlert(p, 1);
        hospital.addManualCall(p);

        assertEquals(1, hospital.getHighAlertCount());
        assertEquals(1, hospital.getLowAlertCount());
    }

    @Test
    void testDispatchPriority() {
        Hospital hospital = new Hospital(10, 1);
        Nurse nurse = new Nurse("TestNurse",Simulation.getResolutionChance());
        hospital.addNurse(nurse);

        Patient highPat = new Patient();
        Patient lowPat = new Patient();

        hospital.addAlert(lowPat, 1);
        hospital.addAlert(highPat, 2);

        hospital.dispatchStaff();

        assertEquals(1, hospital.getHighAlertCount());
        assertEquals(1, hospital.getLowAlertCount());
    }

    @Test
    void testBuzzerPriorityOverLowSeverity() {
        Hospital hospital = new Hospital(10, 1);
        Nurse nurse = new Nurse("TestNurse", Simulation.getResolutionChance());
        hospital.addNurse(nurse);

        Patient lowPat = new Patient();
        Patient buzzerPat = new Patient();

        hospital.addAlert(lowPat, 1);
        hospital.addManualCall(buzzerPat);

        hospital.dispatchStaff();

        assertEquals(1, hospital.getLowAlertCount());
    }
}