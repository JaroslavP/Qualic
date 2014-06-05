package bms;

public class BridgePasport {

    public String name;
    public String barrier;
    public String Category;
    public int lines;
    public String locality;
    public double distance;
    public int input;
    public String company;
    public int lifetime;
    public int repair;
    public String imgPath;

    public BridgePasport (String a, String b, String c, int d, String e,
                          double f, int g, String h, int i, int j, String k) {
        name = a;
        barrier = b;
        Category = c;
        lines = d;
        locality = e;
        distance = f;
        input = g;
        company = h;
        lifetime = i;
        repair = j;
        imgPath = k;
    }
}
