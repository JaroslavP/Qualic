package bms;

public class Rating {
    public int categoruStatus;
    public double wear;
    public double capacity;
    public String safety;
    public String longevity;
    public double rDeck;
    public double rSuperStruct;
    public double rRegulStruct;
    public double rSupport;

    public Rating () {
        categoruStatus = 0;
        wear = 0;
        capacity = 0;
        safety = "";
        longevity = "";
        rDeck = 0;
        rSuperStruct = 0;
        rRegulStruct = 0;
        rSupport = 0;
    }
}