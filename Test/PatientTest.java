import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    @Test
    void TestPatientUniqueness(){
        Patient p1 = new Patient("testName1", "01/01/2000");
        Patient p2 = new Patient("testName2", "01/01/2000");

        assertNotNull(p1.getID());
        assertNotNull(p2.getID());

        assertNotEquals(p1.getID(), p2.getID());
    }

    @Test
    void getIDTest(){
        Patient p1 = new Patient("testName1", "01/01/2000");
        assertNotNull(p1.getID());
    }




}