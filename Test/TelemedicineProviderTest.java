import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TelemedicineProviderTest {

    @Test
    public void testSessionLimit() {
        TelemedicineProvider tele = new TelemedicineProvider(2);

        assertTrue(tele.requestSession());
        assertTrue(tele.requestSession());
        assertFalse(tele.requestSession());

        assertEquals(0, tele.getAvailable());
    }

    @Test
    public void testReleaseSession() {
        TelemedicineProvider tele = new TelemedicineProvider(1);

        tele.requestSession();
        assertFalse(tele.requestSession());

        tele.releaseSession();
        assertEquals(1, tele.getAvailable());
        assertTrue(tele.requestSession());
    }
}