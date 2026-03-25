import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertQueueManagerTest {
    @Test
    public void testPriorityRouting() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();
        Alert low = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert emergency = new Alert(p, AlertSeverity.TIER3_EMERGENCY);

        manager.addAlert(low);
        manager.addAlert(emergency);

        assertEquals(AlertSeverity.TIER3_EMERGENCY, manager.getNextAlert().getSeverity());
        assertEquals(AlertSeverity.TIER1_NONURGENT, manager.getNextAlert().getSeverity());
    }
}