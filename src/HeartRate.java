import java.util.Random;

public class HeartRate extends Observation{
    private int rate;

    public HeartRate(){
        super();

        Random local=new Random();
        double gaussian=local.nextGaussian();

        double mean=85.0;
        double stdDev=15.0
    }
}