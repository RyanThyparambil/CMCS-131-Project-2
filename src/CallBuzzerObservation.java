public class CallBuzzerObservation {
    public static class callBuzzerObservation extends Observation {
        private boolean pressed;

        public callBuzzerObservation() {
            super();   // <-- REQUIRED so Observation.time is set correctly
            this.pressed = (Simulation.getRandomInt(1, 100) <= 5);
        }

        @Override
        public String getValueString() {
            return "Call Buzzer Pressed: " + pressed;
        }

        @Override
        public int checkCondition() {
            return pressed ? 3 : 0;
        }
    }
}
