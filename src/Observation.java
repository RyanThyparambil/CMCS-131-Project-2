public abstract class Observation {

    protected int time;

    public Observation() {
        this.time = Simulation.getCurrentTime();
    }

    public int getTime() {
        return time;
    }

    public abstract String getValueString();
    public abstract int checkCondition();
}