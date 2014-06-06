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
        if (specific.length.equals("менше 21м"))
            rating.wear += rating.rDeck*0.2 + rating.rSuperStruct*0.45 + rating.rSupport*0.25 + rating.rRegulStruct*0.1;
        if (specific.length.equals("22-28м"))
            rating.wear += rating.rDeck*0.2 + rating.rSuperStruct*0.4 + rating.rSupport*0.3 + rating.rRegulStruct*0.1;
        if (specific.length.equals("29-35м"))
            rating.wear += rating.rDeck*0.17 + rating.rSuperStruct*0.4 + rating.rSupport*0.33 + rating.rRegulStruct*0.1;
        if (specific.length.equals("36-42м"))
            rating.wear += rating.rDeck*0.15 + rating.rSuperStruct*0.4 + rating.rSupport*0.36 + rating.rRegulStruct*0.09;
        if (specific.length.equals("43-62м"))
            rating.wear += rating.rDeck*0.15 + rating.rSuperStruct*0.39 + rating.rSupport*0.4 + rating.rRegulStruct*0.06;
        if (specific.length.equals("63-83м"))
            rating.wear += rating.rDeck*0.13 + rating.rSuperStruct*0.36 + rating.rSupport*0.46 + rating.rRegulStruct*0.05;
        if (specific.length.equals("більше 84м"))
            rating.wear += rating.rDeck*0.11 + rating.rSuperStruct*0.35 + rating.rSupport*0.5 + rating.rRegulStruct*0.04;
    }

    public BridgePasport getBridgePasport() { return bridgePasport; }
    public Specific getSpecific() { return specific; }
    public Deck getDeck() { return deck; }
    public SuperStructure getSuperStructure() { return superStructure; }
    public RegulatoryStructure getRegulatoryStructure() { return regulatoryStructure; }
    public Support getSupport() { return support; }
    public Rating getRating() { return rating; }
}
