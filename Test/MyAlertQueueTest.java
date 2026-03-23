import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MyAlertQueueTest {


    @Test
    void testEnqueueIncreasesSize() {
        MyAlertQueue queue = new MyAlertQueue(3);
        Patient p = new Patient();
        Alert a = new Alert(p, AlertSeverity.TIER1_NONURGENT);


        queue.enqueue(a);
        assertEquals(1, queue.getSize());
    }


    @Test
    void testDequeueDecreasesSize() {
        MyAlertQueue queue = new MyAlertQueue(3);
        Patient p = new Patient();
        Alert a = new Alert(p, AlertSeverity.TIER1_NONURGENT);


        queue.enqueue(a);
        queue.dequeue();


        assertEquals(0, queue.getSize());
    }


    @Test
    void testFIFOOrder() {
        MyAlertQueue queue = new MyAlertQueue(5);
        Patient p = new Patient();


        Alert a1 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert a2 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert a3 = new Alert(p, AlertSeverity.TIER3_EMERGENCY);


        queue.enqueue(a1);
        queue.enqueue(a2);
        queue.enqueue(a3);


        assertEquals(a1, queue.dequeue());
        assertEquals(a2, queue.dequeue());
        assertEquals(a3, queue.dequeue());
    }


    @Test
    void testDequeueOnEmptyReturnsNull() {
        MyAlertQueue queue = new MyAlertQueue(3);
        assertNull(queue.dequeue());
    }


    @Test
    void testQueueDoesNotOverflow() {
        MyAlertQueue queue = new MyAlertQueue(2);
        Patient p = new Patient();


        Alert a1 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert a2 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert a3 = new Alert(p, AlertSeverity.TIER3_EMERGENCY);


        queue.enqueue(a1);
        queue.enqueue(a2);
        queue.enqueue(a3); // should be ignored silently


        assertEquals(2, queue.getSize());
        assertEquals(a1, queue.dequeue());
        assertEquals(a2, queue.dequeue());
        assertNull(queue.dequeue());
    }


    @Test
    void testQueueShiftsElementsCorrectly() {
        MyAlertQueue queue = new MyAlertQueue(5);
        Patient p = new Patient();


        Alert a1 = new Alert(p, AlertSeverity.TIER1_NONURGENT);
        Alert a2 = new Alert(p, AlertSeverity.TIER2_WARNING);
        Alert a3 = new Alert(p, AlertSeverity.TIER3_EMERGENCY);


        queue.enqueue(a1);
        queue.enqueue(a2);
        queue.enqueue(a3);


        queue.dequeue(); // removes a1, shifts a2 → index 0, a3 → index 1


        Alert next = queue.dequeue();
        assertEquals(a2, next);
    }
}

