package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter {
    static private final int MAX_WEAPONS = 2;
    static private final int MAX_SHIELDS = 3;
    static private final int INITIAL_HEALTH = 10;
    static private final int HITS2LOSE = 3;

    private char number;
    private int consecutiveHits;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;

    public Player(char number, float intelligence, float strength) {
        super(("Player #" + number), intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        this.consecutiveHits = 0;
        this.weapons = new ArrayList<>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);

        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();

        for (int i = 0; i < MAX_WEAPONS; i++) {
            Weapon w = weaponCardDeck.nextCard();
            weapons.add(w);
        }
        for (int i = 0; i < MAX_SHIELDS; i++) {
            Shield s = shieldCardDeck.nextCard();
            shields.add(s);
        }
    }

    public Player(Player other) {
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        this.weapons = new ArrayList<>(other.weapons);
        this.shields = new ArrayList<>(other.shields);
    }

    public void resurrect() {
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
    }

    public char getNumber() {
        return number;
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if ((size > 0) && !contained) {
            return validMoves.get(0);
        } else {
            return direction;
        }
    }

    @Override
    public float attack() {
        return sumWeapons() + getStrength();
    }

    @Override
    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }

    public void receivedReward() {
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();

        for (int i = 0; i < wReward; i++) {
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        for (int i = 0; i < sReward; i++) {
            Shield snew = newShield();
            receiveShield(snew);
        }

        int extraHealth = Dice.healthReward();
        setHealth(getHealth() + extraHealth);
    }

    @Override
    public String toString() {
        String wString = "";
        String sString = "";
        for (int i = 0; i < weapons.size(); i++) {
            wString += weapons.get(i).toString() + " ";
        }
        for (int i = 0; i < shields.size(); i++) {
            sString += shields.get(i).toString() + " ";
        }
        return number + " (HP: " + getHealth() + ", Pos: [" + getRow() + "," + getCol() + "], INT:" + getIntelligence()
                + ", STR:" + getStrength()
                + ") WEAPONS: " + wString + "SHIELDS: " + sString + "\n";
    }

    private void receiveWeapon(Weapon w) {
        for (int i = 0; i < weapons.size(); i++) {
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            if (discard) {
                weapons.remove(wi);
            }
        }

        int size = weapons.size();
        if (size < MAX_WEAPONS) {
            weapons.add(w);
        }
    }

    private void receiveShield(Shield s) {
        for (int i = 0; i < shields.size(); i++) {
            Shield si = shields.get(i);
            boolean discard = si.discard();
            if (discard) {
                shields.remove(si);
            }
        }

        int size = shields.size();
        if (size < MAX_SHIELDS) {
            shields.add(s);
        }
    }

    private Weapon newWeapon() {
        return weaponCardDeck.nextCard();
    }

    private Shield newShield() {
        return shieldCardDeck.nextCard();
    }

    protected float sumWeapons() {
        float sum = 0;
        for (int i = 0; i < weapons.size(); i++) {
            sum += weapons.get(i).attack();
        }
        return sum;
    }

    protected float sumShields() {
        float sum = 0;
        for (int i = 0; i < shields.size(); i++) {
            sum += shields.get(i).protect();
        }
        return sum;
    }

    protected float defensiveEnergy() {
        return sumShields() + getIntelligence();
    }

    private boolean manageHit(float receivedAttack) {
        float defense = defensiveEnergy();

        if (defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }

        boolean lose;
        if ((consecutiveHits == HITS2LOSE) || (dead())) {
            resetHits();
            lose = true;
        } else {
            lose = false;
        }

        return lose;
    }

    private void resetHits() {
        consecutiveHits = 0;
    }

    private void incConsecutiveHits() {
        consecutiveHits++;
    }
}