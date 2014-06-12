package bms.Bridge;

public class Specific {

    public String length;
    public int roadWidth;
    public int leftWalk;
    public int rightWalk;
    public int fence;
    public int walkFence;
    public double angle;
    public int load;
    public int fLoad;
    public String pathShema;

    public Specific (String a, int b, int c, int d, int e, int f,
                     double g, int h, int i, String j) {
        length = a;
        roadWidth = b;
        leftWalk = c;
        rightWalk = d;
        fence = e;
        walkFence = f;
        angle = g;
        load = h;
        fLoad = i;
        pathShema = j;
    }
}