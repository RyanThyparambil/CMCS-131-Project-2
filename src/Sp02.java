import java.util.Random;

public class Sp02 extends Observation {
    private double saturation;

    public Sp02() {
        super();   // Correct: captures Simulation.getCurrentTime()

        Random r = new Random();
        saturation = 98.0 + r.nextGaussian() * 3.0;

        if (saturation > 100) saturation = 100.0;
        if (saturation < 0) saturation = 0;
    }

    @Override
    public String getValueString() {
        return "SpO2: " + (int)saturation + "%";
    }

    public double getSaturation() {
        return saturation;
    }

    @Override
    public int checkCondition() {
        if (saturation < 88) return 2;
        if (saturation < 92) return 1;
        return 0;
    }
}
