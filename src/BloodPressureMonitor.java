public class BloodPressureMonitor extends Device {
    private Observation[] observations = new Observation[10];

    @Override
    public Observation read() {
        BloodPressure reading = new BloodPressure();
        for (int i = observations.length - 1; i > 0; i--) {
            observations[i] = observations[i - 1];
        }
        observations[0] = reading;
        return reading;
    }
}
