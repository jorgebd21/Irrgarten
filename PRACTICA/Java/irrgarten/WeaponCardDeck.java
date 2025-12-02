package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon>{
    @Override
    protected void addCards() {
        for(int i = 0; i < 10; i++){
            Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(w);
        }
    }
    
}
