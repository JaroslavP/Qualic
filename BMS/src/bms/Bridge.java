package bms;

/**
 * Created by computer on 05.06.2014.
 */
public class Bridge {
    private Deck deck;

    public Deck getDeck() { return deck; }

    public Bridge (Deck deckValue) {
        deck = deckValue;
    }
}
