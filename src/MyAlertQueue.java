public class MyAlertQueue {
    private Alert[] elements;
    private int size;
    private int capacity;

    public MyAlertQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new Alert[capacity];
        this.size = 0;
    }

    public void enqueue(Alert alert) {
        if (size < capacity) {
            elements[size] = alert;
            size++;
        }
    }
    public Alert dequeue() {
        if (size == 0) {
            return null;
        }
        Alert front = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return front;
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}