import java.util.Random;

public class HeartRate extends Observation{
    private double rate;
    private int addOrSubtract;
    private double value;

    public HeartRate(){
        super();

        Random local=new Random();
        double gaussian=local.nextGaussian();

        double mean=85.0;
        double stdDev=15.0;


        addOrSubtract=simulation.getRandomInt(1,2);

        if(addOrSubtract==1){
            value=85.0+(gaussian*15.0);
        } else if (addOrSubtract==2) {
            value=85.0-(gaussian*15.0);
        }
        rate=value;
    }

    @Override
    public String getValueString() {
        return "Heart Rate: "+rate;
    }

    public double getRate(){
        return rate;
    }
}