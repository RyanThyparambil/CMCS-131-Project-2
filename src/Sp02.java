import java.util.Random;

public class SpO2 extends Observation {
    private double saturation;

    public SpO2() {
        super();
        Random local = new Random();

        double val = 98.0 + (local.nextGaussian() * 3.0);

        if (val > 100.0) val = 100.0;
        this.saturation = val;
    }

    @Override
    public String getValueString() {
        return "SpO2: " + (int)saturation + "%";
    }

    public double getSaturation() { return saturation; }

    @Override
    public int checkCondition() {
        if (saturation < 88) {
            return 2;
        } else if (saturation < 92) {
            return 1;
        }
        return 0;
    }
}
