import java.util.Random;

public class HeartRate extends Observation{

    private double rate;

    public HeartRate() {
        super();

        Random r = new Random();
        rate = 85 +r.nextGaussian() * 15 + Simulation.getRandomInt(-2, 2);
    }

    public double getRate() {
        return rate;
    }
    @Override
    public String getValueString() {
        return "Heart Rate: "+rate;
    }

    @Override
    public int checkCondition(){
        if (rate > 150 || rate < 40) return 2;
        if (rate > 110 || rate < 55) return 1;
        return 0;
    }
}