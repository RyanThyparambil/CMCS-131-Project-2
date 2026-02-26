import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    void TestPatientUniqueness(){
        Patient p1 = new Patient();
        Patient p2 = new Patient();

        assertNotNull(p1.getID());
        assertNotNull(p2.getID());

        assertNotEquals(p1.getID(), p2.getID());
    }


}