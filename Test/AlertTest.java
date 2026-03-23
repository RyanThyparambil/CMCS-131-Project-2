import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AlertTest {

    @Nested
    class AlertSeverityTest {


        @Test
        void testEnumContainsExpectedValues() {
            assertNotNull(AlertSeverity.valueOf("TIER1_NONURGENT"));
            assertNotNull(AlertSeverity.valueOf("TIER2_WARNING"));
            assertNotNull(AlertSeverity.valueOf("TIER3_EMERGENCY"));
            assertNotNull(AlertSeverity.valueOf("MANUAL"));
        }


        @Test
        void testEnumCount() {
            // If someone adds or removes severities, this test will catch it
            assertEquals(4, AlertSeverity.values().length);
        }
    }

}
