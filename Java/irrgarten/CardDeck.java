package irrgarten;

import java.util.ArrayList;

public abstract class CardDeck<T> {
    private ArrayList<T> cardDeck;
    static protected final int NUM_BARAJA = 10;

    public CardDeck() {
        cardDeck = new ArrayList<>();
    }

    protected abstract void addCards();

    protected void addCard(T card) {
        cardDeck.add(card);
    }

    public T nextCard() {
        if (cardDeck.isEmpty()) {
            addCards();
        }
        T output = cardDeck.get(0);
        cardDeck.remove(0);
        return output;
    }
}
