import java.util.UUID;

public class Patient {

    private UUID id;

    public Patient(){
        this.id=UUID.randomUUID();
    }

    public UUID getID(){
        return id;
    }


}
