public class MyAlertQueue {
    private class QueueRecord {
        public Alert alert;
        public QueueRecord next;

        public QueueRecord(Alert alert) {
            this.alert = alert;
            this.next = null;
        }
    }

    private QueueRecord head;
    private QueueRecord tail;

    public MyAlertQueue() {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(Alert alert) {
        QueueRecord newRecord = new QueueRecord(alert);
        if (isEmpty()) {
            head = newRecord;
            tail = newRecord;
        } else {
            tail.next = newRecord;
            tail = newRecord;
        }
    }

    public Alert dequeue() {
        if (isEmpty()) {
            return null;
        }
        Alert alert = head.alert;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return alert;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int count() {
        int count = 0;
        QueueRecord current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}