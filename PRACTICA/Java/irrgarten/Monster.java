package irrgarten;

public class Monster extends LabyrinthCharacter{
    static private final int INITIAL_HEALTH = 5;

    public Monster(String name, float intelligence, float strength) {
        super(name, intelligence, strength, INITIAL_HEALTH);
    }

    @Override
    public float attack() {
        return Dice.intensity(super.getStrength());
    }

    @Override
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        if (!isDead) {
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            if (defensiveEnergy < receivedAttack) {
                gotWounded();
                dead();
            }
        }
        return isDead;
    }
}
