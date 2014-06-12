package bms.Bridge;

public class SMETA {

    public int Num;
    public String BridgeElement;
    public String Name;
    public double unit;
    public double number;
    public double numberCost;
    public double unitWork;
    public double sumNumbercost;
    public double sumUnitWork;
    public double total;

    public SMETA(int a, String b, String c, double d,
                 double e, double f, double g, double d5, double d6, double d7) {
        Num = a;
        BridgeElement = b;
        Name = c;
        unit = d;
        number = e;
        numberCost = f;
        unitWork = g;
        sumNumbercost = d5;
        sumUnitWork = d6;
        total = d7;
    }

    public SMETA () {
        Num = 0;
        BridgeElement = "";
        Name = "";
        unit = 0;
        number = 0;
        numberCost = 0;
        unitWork = 0;
        sumNumbercost = 0;
        sumUnitWork = 0;
        total = 0;
    }
}
