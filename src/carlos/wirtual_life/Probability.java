package carlos.wirtual_life;

public enum Probability {
    GRASS(0.15),
    MILT(0.05),
    GUARANA(0.10),
    WOLFBERRY(0.10),
    SOSNOWSKYHOGWEED(0.05) ;

    private final double amount ;

    Probability(double amount){
        this.amount = amount ;
    }

    public double getProbability() {
        return amount;
    }
}
