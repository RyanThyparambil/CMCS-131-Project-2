public class Simulation {
    public boolean WritePatients(String filename) {
        boolean result = true;
        File file = new File(filename);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < patients().length; i++) {
                if (patients()[i] != null) {
                    writer.write(patients()[i].toCSV() + "\n");
                }
            }
        }
    }
