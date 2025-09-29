package irrgarten;

import java.util.Random;

public class Dice {
    static private int MAX_USES = 5;
    static private float MAX_INTELLIGENCE = 10f;
    static private float MAX_STRENGTH = 10f;
    static private float RESURRECT_PROB = 0.3f;
    static private int WEAPON_REWARD = 2;
    static private int SHIELD_REWARD = 3;
    static private int HEALTH_REWARD = 5;
    static private int MAX_ATTACKS = 3;
    static private int MAX_SHIELD = 2;

    static private Random generator = new Random();

    static public int randomPos(int max){
        return generator.nextInt(max);
    }
}
