package irrgarten;

public class FuzzyPlayer extends Player {
    public FuzzyPlayer(Player other) {
        super(other);
    }

    @Override
    public Directions move(Directions direction, java.util.ArrayList<Directions> validMoves) {
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if ((size > 0) && !contained) {
            return Dice.nextStep(direction, validMoves, getIntelligence());
        } else {
            return direction;
        }
    }

    @Override
    public float attack() {
        return sumWeapons() + Dice.intensity(getStrength());
    }

    @Override
    public float defensiveEnergy(){
        return sumShields() + Dice.intensity(getIntelligence());
    }

    @Override
    public String toString() {
        return "Fuzzy" + super.toString();
    }
}
