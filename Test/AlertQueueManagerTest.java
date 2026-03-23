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

    @Test
    void testEmptyQueueReturnsNull() {
        AlertQueueManager manager = new AlertQueueManager();

        assertNull(manager.popTier1());
        assertNull(manager.popTier2());
        assertNull(manager.popTier3());
        assertNull(manager.popManual());
    }

    @Test
    void testQueuesAreIndependent() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();

        Alert t1 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert t2 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert t3 = new Alert(p, AlertSeverity.TIER3_EMERGENCY);
        Alert manual = new Alert(p, AlertSeverity.MANUAL);

        manager.addAlert(t1);
        manager.addAlert(t2);
        manager.addAlert(t3);
        manager.addManual(manual);

        assertEquals(t3, manager.popTier3());
        assertEquals(t2, manager.popTier2());
        assertEquals(t1, manager.popTier1());
        assertEquals(manual, manager.popManual());
    }

    @Test
    void testMultipleQueuesSimultaneously() {
        AlertQueueManager manager = new AlertQueueManager();
        Patient p = new Patient();

        Alert a1 = new Alert(p, AlertSeverity.TIER3_EMERGENCY);
        Alert a2 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert a3 = new Alert(p, AlertSeverity.MANUAL);
        Alert a4 = new Alert(p, AlertSeverity.TIER2_WARNING);

        manager.addAlert(a1);
        manager.addAlert(a2);
        manager.addManual(a3);
        manager.addAlert(a4);

        assertEquals(a1, manager.popTier3());
        assertEquals(a4, manager.popTier2());
        assertEquals(a2, manager.popTier1());
        assertEquals(a3, manager.popManual());
    }
}
