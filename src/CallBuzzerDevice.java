public class CallBuzzerDevice extends Device {

    public Observation read() {
        return new CallBuzzerObservation();
    }
}