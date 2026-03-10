import java.util.Random;
public class BloodPressure {
    public class bloodPressure extends Observation {
        private double systolic;
        private double diastolic;

        public bloodPressure() {
            super();
            Random local = new Random();

            this.systolic = 120.0 + (local.nextGaussian() * 20.0) + Simulation.getRandomInt(-2, 2);
            this.diastolic = 80.0 + (local.nextGaussian() * 10.0) + Simulation.getRandomInt(-1, 1);
        }

        @Override
        public String getValueString() {
            return "Blood Pressure: " + (int)systolic + "/" + (int)diastolic;
        }

        public double getSystolic() { return systolic; }
        public double getDiastolic() { return diastolic; }

        @Override
        public int checkCondition() {
            if (systolic > 180 || systolic < 80 || diastolic > 120 || diastolic < 40) {
                return 2;
            } else if (systolic > 140 || systolic < 90 || diastolic > 90 || diastolic < 60) {
                return 1;
            }
            return 0;
        }
    }

}
