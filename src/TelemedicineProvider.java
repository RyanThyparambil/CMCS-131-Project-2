public class TelemedicineProvider {
    private int totalSessions;
    private int sessionsInUse;

    public TelemedicineProvider(int maxSessions) {
        this.totalSessions = maxSessions;
        this.sessionsInUse = 0;
    }

    public boolean requestSession() {
        if (sessionsInUse < totalSessions) {
            sessionsInUse++;
            return true;
        }
        return false;
    }

    public void releaseSession() {
        if (sessionsInUse > 0) {
            sessionsInUse--;
        }
    }

    public int getAvailable() {
        return totalSessions - sessionsInUse;
    }
}