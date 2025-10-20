package irrgarten;

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

    private Weapon[] weapons;
    private Shield[] shields;

    public Player(char number, float intelligence, float strength) {
        this.name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
        this.weapons = new Weapon[MAX_WEAPONS];
        this.shields = new Shield[MAX_SHIELDS];
    }

    public void resurrect() {
        weapons = new Weapon[MAX_WEAPONS];
        shields = new Shield[MAX_SHIELDS];
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
        this.row = row;
        this.col = col;
    }
    public boolean dead() {
        return health<0;
    }
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }
    public float attack(){
        float sum = strength;
        for(int i=0; i<MAX_WEAPONS ; i++){
            sum = weapons[i].attack();
        }
        return sum;
    }
    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    public void receivedReward(){

    }
    public String toString(){
        return name + " (HP: " + health + ", Pos: [" + row + "," + col + "], INT:" + intelligence + ", STR:" + strength + ")";
    }

    private void receiveWeapon(Weapon w){

    }
    private void receiveShield(Shield s){

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
            sum += weapons[i].attack();
        }
        return sum;
    }
    private float sumShields(){
        float sum = 0;
        for(int i=0; i<MAX_SHIELDS ; i++){
            sum += shields[i].protect();
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
