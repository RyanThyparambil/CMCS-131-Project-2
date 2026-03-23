public class CallBuzzerObservation extends Observation {

    private boolean pressed;

    public CallBuzzerObservation() {
        super();
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
