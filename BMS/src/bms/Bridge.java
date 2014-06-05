package bms;

public class Bridge {

    private Deck deck;
    private SuperStructure superStructure;
    private RegulatoryStructure regulatoryStructure;
    private Support support;

    public Bridge (Deck deckValue, SuperStructure ssValue,
                   RegulatoryStructure rS, Support supp) {
        deck = deckValue;
        superStructure = ssValue;
        regulatoryStructure = rS;
        support = supp;

    }

    public Deck getDeck() { return deck; }
    public SuperStructure getSuperStructure() { return superStructure; }
    public RegulatoryStructure getRegulatoryStructure() { return regulatoryStructure; }
    public Support getSupport() { return support; }
}
