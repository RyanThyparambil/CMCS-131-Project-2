public class CallBuzzerDevice {
    public Observation read() {
        return null;
    }
}

    class callBuzzerDevice extends Device {

        public Observation read() {
            return new callBuzzerDevice().read();
        }
    }


