package bms;

import java.util.GregorianCalendar;

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
        int intCapacity = 0;
        int intSafety = 0;
        int intLongevity = 0;
        int intDeck = 0;
        int intSuperStruct = 0;
        int intSupport = 0;
        int intRegulStruct = 0;
        // DECK rating
        rating.rDeck += deck.coating + deck.drainage + deck.expensionJoins + deck.fence + deck.sidewalk;
        if (rating.rDeck <= 2)
            intDeck = 1;
        else  if ((rating.rDeck > 2) && (rating.rDeck <= 10))
            intDeck = 2;
        else if ((rating.rDeck > 10) && (rating.rDeck <= 40))
            intDeck = 3;
        else if ((rating.rDeck > 40) && (rating.rDeck <= 60))
            intDeck = 4;
        else if (rating.rDeck > 60)
            intDeck = 5;
        // SuperSTRUCK
        rating.rSuperStruct += superStructure.crack + superStructure.powerCrack + superStructure.corrosion
                + superStructure.damageRust + superStructure.disSeam + superStructure.defBeam
                + superStructure.resBeam + superStructure.crackBolt;
        if (rating.rSuperStruct <= 2)
            intSuperStruct = 1;
        else  if ((rating.rSuperStruct > 2) && (rating.rSuperStruct <= 10))
            intSuperStruct = 2;
        else if ((rating.rSuperStruct > 10) && (rating.rSuperStruct <= 30))
            intSuperStruct = 3;
        else if ((rating.rSuperStruct > 40) && (rating.rSuperStruct <= 50))
            intSuperStruct = 4;
        else if (rating.rSuperStruct > 50)
            intSuperStruct = 5;
        // REGULTORY STRUCTURE
        rating.rRegulStruct += regulatoryStructure.areaDamege + regulatoryStructure.track
                + regulatoryStructure.speedLimit + regulatoryStructure.cone;
        if (rating.rRegulStruct <= 2)
            intRegulStruct = 1;
        else  if ((rating.rRegulStruct > 2) && (rating.rRegulStruct <= 20))
            intRegulStruct = 2;
        else if ((rating.rRegulStruct > 20) && (rating.rRegulStruct <= 50))
            intRegulStruct = 3;
        else if ((rating.rRegulStruct > 50) && (rating.rRegulStruct <= 60))
            intRegulStruct = 4;
        else if (rating.rRegulStruct > 60)
            intRegulStruct = 5;
        // SUPPORT
        rating.rSupport += support.header + support.bodySupport + support.foundation;
        if (rating.rSupport <= 2)
            intSupport = 1;
        else  if ((rating.rSupport > 2) && (rating.rSupport <= 10))
            intSupport = 2;
        else if ((rating.rSupport > 10) && (rating.rSupport <= 30))
            intSupport = 3;
        else if ((rating.rSupport > 30) && (rating.rSupport <= 60))
            intSupport = 4;
        else if (rating.rSupport > 60)
            intSupport = 5;
        // WEAR
        if (specific.length.equals("менше 21м"))
            rating.wear += rating.rDeck * 0.2 + rating.rSuperStruct * 0.45 + rating.rSupport * 0.25 + rating.rRegulStruct * 0.1;
        if (specific.length.equals("22-28м"))
            rating.wear += rating.rDeck * 0.2 + rating.rSuperStruct * 0.4 + rating.rSupport * 0.3 + rating.rRegulStruct * 0.1;
        if (specific.length.equals("29-35м"))
            rating.wear += rating.rDeck * 0.17 + rating.rSuperStruct * 0.4 + rating.rSupport * 0.33 + rating.rRegulStruct * 0.1;
        if (specific.length.equals("36-42м"))
            rating.wear += rating.rDeck * 0.15 + rating.rSuperStruct * 0.4 + rating.rSupport * 0.36 + rating.rRegulStruct * 0.09;
        if (specific.length.equals("43-62м"))
            rating.wear += rating.rDeck * 0.15 + rating.rSuperStruct * 0.39 + rating.rSupport * 0.4 + rating.rRegulStruct * 0.06;
        if (specific.length.equals("63-83м"))
            rating.wear += rating.rDeck * 0.13 + rating.rSuperStruct * 0.36 + rating.rSupport * 0.46 + rating.rRegulStruct * 0.05;
        if (specific.length.equals("більше 84м"))
            rating.wear += rating.rDeck * 0.11 + rating.rSuperStruct * 0.35 + rating.rSupport * 0.5 + rating.rRegulStruct * 0.04;
        // REDUCTION IN CAPACITY
        rating.capacity = (1 - ((double)specific.fLoad / (double)specific.load)) * 100;
        if (rating.capacity <= 2)
            intCapacity = 1;
        else  if ((rating.capacity > 2) && (rating.capacity <= 10))
            intCapacity = 2;
        else if ((rating.capacity > 10) && (rating.capacity <= 30))
            intCapacity = 3;
        else if ((rating.capacity > 30) && (rating.capacity <= 60))
            intCapacity = 4;
        else if (rating.capacity > 60)
            intCapacity = 5;
        // SAFETY
        if (bridgePasport.Category.equals("Магістраль 120км/год")) {
            if (rating.rDeck <= 2) {
                rating.safety = "Дозволенна максимальна швидкіть в 120 км/год";
                intSafety = 1;}
            else if ((rating.rDeck > 2) && (rating.rDeck <= 10)) {
                rating.safety = "Максимальна швидкіть руху 90 км/год";
                intSafety = 2;}
            else if ((rating.rDeck > 10) && (rating.rDeck <= 40)) {
                rating.safety = "Максимальна швидкіть руху " + 60*0.75 + "-60 км/год";
                intSafety = 3;}
            else if ((rating.rDeck > 40) && (rating.rDeck <= 60)) {
                rating.safety = "Максимальна швидкіть руху " + 30*0.25 + "-" + 30*0.75 + " км/год";
                intSafety = 4;}
            else if (rating.rDeck > 60) {
                rating.safety = "Максимальна швидкіть руху " + 30*0.25 + " км/год";
                intSafety = 5;}
        } else if (bridgePasport.Category.equals("Загальні 90км/год")) {
            if (rating.rDeck <= 2) {
                rating.safety = "Дозволенна максимальна швидкіть в 90км/год";
                intSafety = 1;}
            else if ((rating.rDeck > 2) && (rating.rDeck <= 10)) {
                rating.safety = "Максимальна швидкіть руху 70км/год";
                intSafety = 2;}
            else if ((rating.rDeck > 10) && (rating.rDeck <= 40)) {
                rating.safety = "Максимальна швидкіть руху " + 50*0.75 + "-50 км/год";
                intSafety = 3;}
            else if ((rating.rDeck > 40) && (rating.rDeck <= 60)) {
                rating.safety = "Максимальна швидкіть руху " + 20*0.25 + "-" + 20*0.75 + " км/год";
                intSafety = 4;}
            else if (rating.rDeck > 60) {
                rating.safety = "Максимальна швидкіть руху " + 20*0.25 + " км/год";
                intSafety = 5;}
        } else if (bridgePasport.Category.equals("Звичайні 60км/год")) {
            if (rating.rDeck <= 2) {
                rating.safety = "Дозволенна максимальна швидкіть в 60 км/год";
                intSafety = 1;}
            else if ((rating.rDeck > 2) && (rating.rDeck <= 10)) {
                rating.safety = "Максимальна швидкіть руху 40 км/год";
                intSafety = 2;}
            else if ((rating.rDeck > 10) && (rating.rDeck <= 40)) {
                rating.safety = "Максимальна швидкіть руху " + 30*0.75 + "-30 км/год";
                intSafety = 3;}
            else if ((rating.rDeck > 40) && (rating.rDeck <= 60)) {
                rating.safety = "Максимальна швидкіть руху " + 20*0.25 + "-" + 20*0.75 + " км/год";
                intSafety = 4;}
            else if (rating.rDeck > 60) {
                rating.safety = "Максимальна швидкіть руху " + 20*0.25 + " км/год";
                intSafety = 5;}
        }
        // LONGEVITY
        double temp = (rating.rSuperStruct + rating.rSupport)/2;
        double temp2 = bridgePasport.lifetime - 2014;
        if (temp <= 2) {
            rating.longevity = "до " + temp2 + " років";
            intLongevity = 1;
        } else if ((temp > 2) && (temp <= 10)) {
            rating.longevity = "до " + temp2*0.9 + "-" + temp2 + " років";
            intLongevity = 2;
        } else if ((temp > 10) && (temp <= 30)) {
            rating.longevity = "до " + temp2*0.75 + "-" + temp2*0.9 + " років";
            intLongevity = 3;
        } else if ((temp > 30) && (temp <= 55)) {
            rating.longevity = "до " + temp2*0.5 + "-" + temp2*0.75 + " років";
            intLongevity = 4;
        } else if (temp > 55) {
            rating.longevity = "менше " + temp2*0.5 + " років";
            intLongevity = 5;
        }
        // CATEGORY STATUS
        rating.categoruStatus = (int) Math.round((intCapacity + intSafety + intLongevity +
                                intDeck + intSuperStruct + intSupport +
                                intRegulStruct)/7.0);
    }

    public BridgePasport getBridgePasport() {
        return bridgePasport;
    }

    public Specific getSpecific() {
        return specific;
    }

    public Deck getDeck() {
        return deck;
    }

    public SuperStructure getSuperStructure() {
        return superStructure;
    }

    public RegulatoryStructure getRegulatoryStructure() {
        return regulatoryStructure;
    }

    public Support getSupport() {
        return support;
    }

    public Rating getRating() {
        return rating;
    }

    public void setBridgePasport(BridgePasport bridgePasport) {
        this.bridgePasport = bridgePasport;
    }

    public void setSpecific(Specific specific) {
        this.specific = specific;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setSuperStructure(SuperStructure superStructure) {
        this.superStructure = superStructure;
    }

    public void setRegulatoryStructure(RegulatoryStructure regulatoryStructure) {
        this.regulatoryStructure = regulatoryStructure;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}