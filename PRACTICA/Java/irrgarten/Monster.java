package irrgarten;

public class Monster extends LabyrinthCharacter {
    static private final int INITIAL_HEALTH = 10;

    public Monster(String name, float intelligence, float strength) {
        super(name, intelligence, strength*100, INITIAL_HEALTH);
    }

    @Override
    public float attack() {
        return Dice.intensity(getStrength());
    }

    @Override
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        if (!isDead) {
            float defensiveEnergy = Dice.intensity(getIntelligence());
            if (defensiveEnergy < receivedAttack) {
                gotWounded();
                dead();
            }
        }
        return isDead;
    }
}
