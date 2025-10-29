package irrgarten;

import java.util.ArrayList;

public class Player {
    static private int MAX_WEAPONS = 2;
    static private int MAX_SHIELDS = 3;
    static private int INITIAL_HEALTH = 10;
    static private int HITS2LOSE = 3;

    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    public Player(char number, float intelligence, float strength) {
        this.name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
        this.weapons = new ArrayList<Weapon>(MAX_WEAPONS);
        this.shields = new ArrayList<Shield>(MAX_SHIELDS);
    }

    public String getName() {
        return name;
    }

    public void resurrect() {
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getNumber() {
        return number;
    }
    public void setPos(int row, int col) {
        assert row >= 0 && col >= 0;
        this.row = row;
        this.col = col;
    }
    public boolean dead() {
        return health<0;
    }
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if((size > 0) && !contained){
            return validMoves.get(0);
        }else{
            return direction;
        }
    }
    public float attack(){
        return sumWeapons() + strength;
    }
    public boolean defend(float receivedAttack){
        manageHit(receivedAttack);
        float defense = defensiveEnergy();

        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }else{
            resetHits();
        }

        boolean lose;
        if(((consecutiveHits == HITS2LOSE) || dead())){
            resetHits();
            lose = true;
        }else{
            lose = false;
        }

        return lose;
    }
    public void receivedReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();

        for(int i=0; i<wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        for(int i=0; i<sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }

        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }
    public String toString(){
        String wString = "";
        String sString = "";
        for(int i=0; i<weapons.size(); i++){
            wString += weapons.get(i).toString() + " ";
        }
        for(int i=0; i<shields.size(); i++){
            sString += shields.get(i).toString() + " ";
        }
        return name + " (HP: " + health + ", Pos: [" + row + "," + col + "], INT:" + intelligence + ", STR:" + strength + ") WEAPONS: " + wString + "SHIELDS: " + sString + "\n";
    }

    private void receiveWeapon(Weapon w){
        for(int i=0; i<weapons.size(); i++){
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            if(discard){
                weapons.remove(wi);
            }
        }

        int size = weapons.size();
        if(size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    private void receiveShield(Shield s){
        for(int i=0; i<shields.size(); i++){
            Shield si = shields.get(i);
            boolean discard = si.discard();
            if(discard){
                shields.remove(si);
            }
        }

        int size = shields.size();
        if(size < MAX_SHIELDS){
            shields.add(s);
        }
    }
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    private float sumWeapons(){
        float sum = 0;
        for(int i=0; i<MAX_WEAPONS ; i++){
            sum += weapons.get(i).attack();
        }
        return sum;
    }
    private float sumShields(){
        float sum = 0;
        for(int i=0; i<MAX_SHIELDS ; i++){
            sum += shields.get(i).protect();
        }
        return sum;
    }
    private float defensiveEnergy(){
        return sumShields() + intelligence;
    }
    private boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    private void resetHits(){
        consecutiveHits = 0;
    }
    private void gotWounded(){
        health--;
    }
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}
