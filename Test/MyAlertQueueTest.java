import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyAlertQueueTest {
    @Test
    public void testQueueOperations() {
        MyAlertQueue queue = new MyAlertQueue();
        Patient p = new Patient();
        Alert a1 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert a2 = new Alert(p, AlertSeverity.TIER2_WARNING);

        assertTrue(queue.isEmpty());
        queue.enqueue(a1);
        queue.enqueue(a2);

        assertEquals(2, queue.count());
        assertFalse(queue.isEmpty());

        assertEquals(a1, queue.dequeue());
        assertEquals(1, queue.count());
        assertEquals(a2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}