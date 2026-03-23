import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AlertQueueManagerTest {


    @Test
    void testTier1AlertGoesToTier1Queue() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();


        Alert alert = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        manager.addAlert(alert);


        assertEquals(alert, manager.popTier1());
        assertNull(manager.popTier1());
    }


    @Test
    void testTier2AlertGoesToTier2Queue() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();


        Alert alert = new Alert(p, AlertSeverity.TIER2_WARNING);
        manager.addAlert(alert);


        assertEquals(alert, manager.popTier2());
        assertNull(manager.popTier2());
    }


    @Test
    void testTier3AlertGoesToTier3Queue() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();


        Alert alert = new Alert(p, AlertSeverity.TIER3_EMERGENCY);
        manager.addAlert(alert);


        assertEquals(alert, manager.popTier3());
        assertNull(manager.popTier3());
    }


    @Test
    void testManualAlertGoesToManualQueue() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();


        Alert alert = new Alert(p, AlertSeverity.MANUAL);
        manager.addManual(alert);


        assertEquals(alert, manager.popManual());
        assertNull(manager.popManual());
    }


    @Test
    void testFIFOOrderingWithinEachQueue() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();


        Alert a1 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert a2 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert a3 = new Alert(p, AlertSeverity.TIER2_WARNING);


        manager.addAlert(a1);
        manager.addAlert(a2);
        manager.addAlert(a3);


        assertEquals(a1, manager.popTier2());
        assertEquals(a2, manager.popTier2());
        assertEquals(a3, manager.popTier2());
    }
}

