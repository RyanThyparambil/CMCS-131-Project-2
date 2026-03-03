import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HospitalTest {

    @Test
    void testAddPatient() {
        Hospital hospital = new Hospital(2);

        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        assertTrue(hospital.addPatient(patient1));
        assertTrue(hospital.addPatient(patient2));

        assertTrue(hospital.getPatientCount() == 2);

        assertTrue(hospital.getPatient(0) == patient1);
        assertTrue(hospital.getPatient(1) == patient2);
    }

    @Test
    void testAddPatientFailsWhenFull() {
        Hospital hospital = new Hospital(2);

        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        Patient patient3 = new Patient();

        hospital.addPatient(patient1);
        hospital.addPatient(patient2);

        assertTrue(hospital.addPatient(patient3) == false);

        assertTrue(hospital.getPatientCount() == 2);
    }

    @Test
    void testGetPatientInvalidIndex() {
        Hospital hospital = new Hospital(2);
        Patient patient1 = new Patient();
        hospital.addPatient(patient1);

        assertTrue(hospital.getPatient(-1) == null);
        assertTrue(hospital.getPatient(5) == null);
    }

    @Test
    void testPatientsArrayLength() {
        Hospital hospital = new Hospital(2);
        assertTrue(hospital.getPatients().length == 2);
    }
}