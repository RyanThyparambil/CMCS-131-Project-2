public class Sp02Monitor extends Device {
    private Observation[] observations = new Observation[10];

    @Override
    public Observation read() {
        SpO2 reading = new SpO2();
        for (int i = observations.length - 1; i > 0; i--) {
            observations[i] = observations[i - 1];
        }
        observations[0] = reading;
        return reading;
    }
}
