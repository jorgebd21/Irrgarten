package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield>{
    @Override
    protected void addCards() {
        for(int i = 0; i < 10; i++){
            Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(s);
        }
    }
}
