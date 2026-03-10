import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpO2Test {
    @Test
    public void testSpO2Bounds() {
        for (int i = 0; i < 100; i++) {
            SpO2 s = new SpO2();
            assertTrue(s.getSaturation() <= 100.0);
        }
    }
}
