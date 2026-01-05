package irrgarten;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
    static private final int MAX_USES = 5;
    static private final float MAX_INTELLIGENCE = 10f;
    static private final float MAX_STRENGTH = 10f;
    static private final float RESURRECT_PROB = 0.3f;
    static private final int WEAPON_REWARD = 2;
    static private final int SHIELD_REWARD = 3;
    static private final int HEALTH_REWARD = 5;
    static private final int MAX_ATTACKS = 3;
    static private final int MAX_SHIELD = 2;

    static private final Random generator = new Random();

    static public int randomPos(int max) {
        return generator.nextInt(max);
    }

    static public int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }

    static public float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }

    static public float randomStrength() {
        return generator.nextFloat() * MAX_STRENGTH;
    }

    static public boolean resurrectPlayer() {
        return generator.nextFloat() < RESURRECT_PROB;
    }

    static public int weaponsReward() {
        return generator.nextInt(WEAPON_REWARD);
    }

    static public int shieldsReward() {
        return generator.nextInt(SHIELD_REWARD);
    }

    static public int healthReward() {
        return generator.nextInt(HEALTH_REWARD);
    }

    static public float weaponPower() {
        return generator.nextFloat() * MAX_ATTACKS;
    }

    static public float shieldPower() {
        return generator.nextFloat() * MAX_SHIELD;
    }

    static public int usesLeft() {
        return generator.nextInt(MAX_USES);
    }

    static public float intensity(float competence) {
        return generator.nextFloat() * competence;
    }

    static public boolean discardElement(int usesLeft) {
        return generator.nextFloat() < 1.0f - (usesLeft / MAX_USES);
    }

    static public Directions nextStep(Directions preferente, ArrayList<Directions> validMoves, float intelligence) {
        if (generator.nextFloat() * MAX_INTELLIGENCE < intelligence) {
            return preferente;
        } else {
            int index = generator.nextInt(validMoves.size());
            return validMoves.get(index);
        }
    }
}
