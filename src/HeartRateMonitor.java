public class HeartRateMonitor extends Device{
    public Observation read(){
        HeartRate rate = new HeartRate();
        return rate;
    }
}
