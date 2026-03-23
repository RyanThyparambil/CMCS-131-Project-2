import java.util.Random;

public class BloodPressure extends Observation {

    private double systolic;
    private double diastolic;

    public BloodPressure() {
        super();

        Random r = new Random();
        systolic = 120 + r.nextGaussian() * 20;
        diastolic = 80 + r.nextGaussian() * 10;
    }

    @Override
    public String getValueString() {
        return "Blood Pressure: " + (int)systolic + "/" + (int)diastolic;
    }

    @Override
    public int checkCondition() {
        if (systolic > 180 || diastolic > 120) return 2;
        if (systolic > 140 || diastolic > 90) return 1;
        return 0;
    }

    public int getSystolic() {
        return (int)systolic;
    }

    public int getDiastolic() {
        return (int)diastolic;
    }
}
