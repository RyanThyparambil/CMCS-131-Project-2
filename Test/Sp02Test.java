import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Sp02Test {

    @Test
    void testSp02ValueInRange() {
        Sp02 sp = new Sp02();
        double value = sp.getSaturation();

        assertTrue(value >= 50 && value <= 100);
    }

    @Test
    void testSeverityIsWithinExpectedRange() {
        Sp02 sp = new Sp02();
        int severity = sp.checkCondition();

        assertTrue(severity >= 0 && severity <= 2);
    }

    @Test
    void testValueStringFormatting() {
        Sp02 sp = new Sp02();
        String s = sp.getValueString();

        assertNotNull(s);
        assertTrue(s.contains("%"));
    }
}
