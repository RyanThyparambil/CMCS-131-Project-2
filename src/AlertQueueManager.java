public class AlertQueueManager {
    private MyAlertQueue tier1Queue;
    private MyAlertQueue tier2Queue;
    private MyAlertQueue tier3Queue;
    private MyAlertQueue manualQueue;
    private final int AGE_THRESHOLD = 30;

    public AlertQueueManager() {
        this.tier1Queue = new MyAlertQueue();
        this.tier2Queue = new MyAlertQueue();
        this.tier3Queue = new MyAlertQueue();
        this.manualQueue = new MyAlertQueue();
    }

    public void addAlert(Alert alert) {
        switch (alert.getSeverity()) {
            case TIER3_EMERGENCY -> tier3Queue.enqueue(alert);
            case MANUAL -> manualQueue.enqueue(alert);
            case TIER2_WARNING -> tier2Queue.enqueue(alert);
            case TIER1_NONURGENT -> tier1Queue.enqueue(alert);
        }
    }

    public Alert getNextAlert() {
        Alert aged = getAgedAlert();
        if (aged != null) {
            return aged;
        }

        if (!tier3Queue.isEmpty()) return tier3Queue.dequeue();
        if (!manualQueue.isEmpty()) return manualQueue.dequeue();
        if (!tier2Queue.isEmpty()) return tier2Queue.dequeue();
        if (!tier1Queue.isEmpty()) return tier1Queue.dequeue();
        return null;
    }

    private Alert getAgedAlert() {
        int now = Simulation.getCurrentTime();

        if (!tier2Queue.isEmpty()) {
            if (now - tier2Queue.peek().getTimeCreated() > AGE_THRESHOLD) {
                return tier2Queue.dequeue();
            }
        }
        if (!tier1Queue.isEmpty()) {
            if (now - tier1Queue.peek().getTimeCreated() > AGE_THRESHOLD) {
                return tier1Queue.dequeue();
            }
        }
        return null;
    }

    public int getUrgentCount() {
        return tier3Queue.count() + manualQueue.count();
    }

    public int getNonUrgentCount() {
        return tier2Queue.count() + tier1Queue.count();
    }

    public int getTotalCount() {
        return tier1Queue.count() + tier2Queue.count() +
                tier3Queue.count() + manualQueue.count();
    }
}