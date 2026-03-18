public class Alert {
    private Patient patient;
    private int severity;
    private int timestamp;

    public Alert(Patient patient, int severity) {
        this.patient = patient;
        this.severity = severity;
        this.timestamp = Simulation.getCurrentTime();
    }

    public Patient getPatient() {
        return patient;
    }

    public int getSeverity() {
        return severity;
    }

    public int getTimestamp() {
        return timestamp;
    }
}