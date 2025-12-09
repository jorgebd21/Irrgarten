package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player {
    public FuzzyPlayer(Player other) {
        super(other);
    }

    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        return Dice.nextStep(direction, validMoves, getIntelligence());
    }

    @Override
    public float attack() {
        return sumWeapons() + Dice.intensity(getStrength());
    }

    @Override
    public float defensiveEnergy() {
        return sumShields() + Dice.intensity(getIntelligence());
    }

    @Override
    public String toString() {
        return "Fuzzy " + super.toString();
    }
}
