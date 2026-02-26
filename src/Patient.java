import java.util.UUID;

public class Patient {

    private UUID id;

    public Patient(){
        this.id=UUID.randomUUID();
    }

    static Patient makePatient(){
        Patient pat = new Patient();
        return pat;
    }

    public UUID getID(){
        return id;
    }


}
