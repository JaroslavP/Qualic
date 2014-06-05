package bms;

public class Bridge {

    private BridgePasport bridgePasport;
    private Specific specific;
    private Rating rating;
    private Deck deck;
    private SuperStructure superStructure;
    private RegulatoryStructure regulatoryStructure;
    private Support support;

    public Bridge (BridgePasport bp, Specific sp, Deck deckValue, SuperStructure ssValue,
                   RegulatoryStructure rS, Support supp) {
        bridgePasport = bp;
        specific = sp;
        rating = new Rating();
        deck = deckValue;
        superStructure = ssValue;
        regulatoryStructure = rS;
        support = supp;
    }

    public void createRating () {
        rating.rDeck += deck.coating + deck.drainage + deck.expensionJoins + deck.fence + deck.sidewalk;
        rating.rSuperStruct += superStructure.crack + superStructure.powerCrack + superStructure.corrosion
                + superStructure.damageRust + superStructure.disSeam + superStructure.defBeam
                + superStructure.resBeam + superStructure.crackBolt;
        rating.rRegulStruct += regulatoryStructure.areaDamege + regulatoryStructure.track
                + regulatoryStructure.speedLimit + regulatoryStructure.cone;
        rating.rSupport += support.header + support.bodySupport + support.foundation;

    }

    public BridgePasport getBridgePasport() { return bridgePasport; }
    public Specific getSpecific() { return specific; }
    public Deck getDeck() { return deck; }
    public SuperStructure getSuperStructure() { return superStructure; }
    public RegulatoryStructure getRegulatoryStructure() { return regulatoryStructure; }
    public Support getSupport() { return support; }
}
