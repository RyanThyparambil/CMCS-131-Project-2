public class HeartRateMonitor extends Device{
    private Observation[] observations = new Observation[10];
    public Observation read() {
        HeartRate reading = new HeartRate();

        for (int i = observations.length - 1; i > 0; i--) {
            observations[i] = observations[i - 1];
        }

        observations[0] = reading;

        return reading;
    }
}
