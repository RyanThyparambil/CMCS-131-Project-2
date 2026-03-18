import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyAlertQueueTest {
    @Test
    public void testEnqueueDequeue() {
        MyAlertQueue queue = new MyAlertQueue(5);
        Patient p = new Patient();
        Alert a1 = new Alert(p, 1);
        Alert a2 = new Alert(p, 2);

        queue.enqueue(a1);
        queue.enqueue(a2);

        assertEquals(2, queue.getSize());
        assertEquals(a1, queue.dequeue());
        assertEquals(1, queue.getSize());
    }

    @Test
    public void testEmptyQueue() {
        MyAlertQueue queue = new MyAlertQueue(5);
        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}