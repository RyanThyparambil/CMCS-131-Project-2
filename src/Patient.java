import java.util.UUID;

public class Patient {

    private UUID id;

    public Patient(){
        this.id=UUID.randomUUID();
    }

    public static Patient createPatient() {
        return new Patient();
    }

    public UUID getID(){
        return id;
    }

    public String toCSV() {
        return id.toString();
    }

    @Override
    public String toString() {
        return "Patient ID: " + id.toString();
    }


}
