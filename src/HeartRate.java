import java.util.Random;

public class HeartRate extends Observation{
    private double rate;
    private int addOrSubtract;
    private double value;

    public HeartRate() {
        super();
        Random local = new Random();

        double gaussian = local.nextGaussian();

        double mean = 85.0;
        double stdDev = 15.0;

        this.rate = mean + (gaussian * stdDev);

        this.rate += Simulation.getRandomInt(-2, 2);
    }

    @Override
    public String getValueString() {
        return "Heart Rate: "+rate;
    }

    public double getRate(){
        return rate;
    }

    public int checkCondition(){
        if (rate > 150 || rate < 40) {
            return 2;
        } else if (rate > 110 || rate < 55) {
            return 1;
        }
        return 0;
    }
}