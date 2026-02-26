import java.util.UUID;

public class Patient {

    private UUID id;
    private String Name;
    private String Dob;

    public Patient(String name, String dob){
        this.id=UUID.randomUUID();
        this.Name=name;
        this.Dob=dob;
    }

    static Patient makePatient(){
        Patient pat = new Patient();
        return pat;
    }

    public UUID getID(){
        return id;
    }


}
