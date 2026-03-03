public class HeartRate extends Observation{
    int prob;
    int rate;
    HeartRate[] rateHistory = new HeartRate[10];
    public HeartRate(){
        prob=Simulation.getRandomInt(1,100);
    }
    static setRate(){
        if(prob<65){
            rate=Simulation.getRandomInt(70, 95);
        }
    }
    public int checkForDangerousTrend(){

    }
    public int dangerous(){
        if(prob<=65){
            return 0;
        }
        if(prob<=85){
            return 2;
        }
        if(prob<=95){
            return 3;
        }
        if(prob<=99){
            return 1;
        }
        if(prob<=100){
            return 4;
        }

    }
}
