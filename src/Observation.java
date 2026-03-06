public abstract class Observation{
    protected int time; //(protected means children of this class can access this)
    public Observation(){
        this.time=Simulation.getCurrentTime();
    }

    public int getTime(){
        return time;
    }
    public abstract String getValueString();
    public abstract int checkCondition();

}