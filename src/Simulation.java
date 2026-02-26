import java.io.*;

public class Simulation {
    public boolean WritePatient(String filename) {
        boolean result = true;
        File file = new File(filename);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < Patient.length; i++) {
                if (Patient[i] != null) {
                    writer.write(Patient[i].toCSV() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Patient Does Not Exist:" + filename);
            result = false;
        }
        return result;
    }
}
