public class Alert {


    private Patient patient;
    private AlertSeverity severity;


    private int timeCreated;
    private int timeResponded;
    private int timeCompleted;


    public Alert(Patient patient, AlertSeverity severity) {
        this.patient = patient;
        this.severity = severity;


        this.timeCreated = Simulation.getCurrentTime();
        this.timeResponded = -1;
        this.timeCompleted = -1;
    }


    public void markResponded() {
        this.timeResponded = Simulation.getCurrentTime();
    }


    public void markCompleted() {
        this.timeCompleted = Simulation.getCurrentTime();
    }


    public Patient getPatient() {
        return patient;
    }


    public AlertSeverity getSeverity() {
        return severity;
    }


    public int getTimeCreated() {
        return timeCreated;
    }


    public int getTimeResponded() {
        return timeResponded;
    }


    public int getTimeCompleted() {
        return timeCompleted;
    }


    public String getDescription() {
        return switch (severity) {
            case TIER1_NONURGENT -> "Non-urgent alert (Tier 1)";
            case TIER2_WARNING -> "Warning alert (Tier 2)";
            case TIER3_EMERGENCY -> "Emergency alert (Tier 3)";
            case MANUAL -> "Manual";
        };
    }


    @Override
    public String toString() {
        return "Alert for Patient: " + patient.getID() +
                " | Severity: " + severity +
                " | Created: " + timeCreated +
                " | Responded: " + timeResponded +
                " | Completed: " + timeCompleted +
                " | Description: " + getDescription();
    }
}


