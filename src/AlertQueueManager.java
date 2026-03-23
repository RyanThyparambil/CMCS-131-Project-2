public class AlertQueueManager {
    private MyAlertQueue tier1Queue = new MyAlertQueue(100);
    private MyAlertQueue tier2Queue = new MyAlertQueue(100);
    private MyAlertQueue tier3Queue = new MyAlertQueue(100);
    private MyAlertQueue manualQueue = new MyAlertQueue(100);


    public void addAlert(Alert alert) {
        switch(alert.getSeverity()) {
            case TIER1_NONURGENT -> tier1Queue.enqueue(alert);
            case TIER2_WARNING -> tier2Queue.enqueue(alert);
            case TIER3_EMERGENCY -> tier3Queue.enqueue(alert);
        }
    }


    public void addManual(Alert alert) {
        manualQueue.enqueue(alert);
    }


    public Alert popTier3() { return tier3Queue.dequeue(); }
    public Alert popTier2() { return tier2Queue.dequeue(); }
    public Alert popTier1() { return tier1Queue.dequeue(); }
    public Alert popManual() { return manualQueue.dequeue(); }
}


