public class MyAlertQueue {
    private Alert[] elements;
    private int size;

    public MyAlertQueue(int capacity) {
        elements = new Alert[capacity];
        size = 0;
    }

    public void enqueue(Alert alert) {
        if (size < elements.length) {
            elements[size] = alert;
            size++;
        }
    }

    public Alert dequeue() {
        if (size == 0) return null;
        Alert front = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return front;
    }

    public int getSize() { return size; }
}