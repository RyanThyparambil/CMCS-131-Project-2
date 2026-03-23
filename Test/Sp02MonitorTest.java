import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Sp02MonitorTest {

    @Test
    void testReadReturnsSp02Observation() {
        Sp02Monitor monitor = new Sp02Monitor();
        Observation obs = monitor.read();

        assertNotNull(obs);
        assertTrue(obs instanceof Sp02);
    }

    @Test
    void testSp02ValueWithinExpectedRange() {
        Sp02Monitor monitor = new Sp02Monitor();
        Sp02 sp = (Sp02) monitor.read();

        double value = sp.getSaturation();



        // SpO2 should be between 50 and 100%
        assertTrue(value >= 50 && value <= 100);
    }

    @Test
    void testMonitorDoesNotReturnNull() {
        Sp02Monitor monitor = new Sp02Monitor();
        assertNotNull(monitor.read());
    }
}
