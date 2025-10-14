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

    public Player(char number, float intelligence, float strength) {

    }

    public void resurrect() {

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
    public setPos(int row, int col) {

    }
    public boolean dead() {

    }
    public Directions move(Directions direction, Directions[] validMoves){

    }
    public float attack(){

    }
    public boolean defend(float receivedAttack){

    }
    public void receivedReward(){

    }
    public String toString(){

    }

    private void receiveWeapon(Weapon w){

    }
    private void receiveShield(Shield s){

    }
    private Weapon newWeapon(){
        
    }
    private Shield newShield(){

    }
    private float sumWeapons(){

    }
    private float sumShields(){

    }
    private float defensiveEnergy(){

    }
    private boolean manageHit(float receivedAttack){

    }
    private void resetHits(){
        
    }
    private void gotWounded(){
        
    }
    private void incConsecutiveHits(){
        
    }
}
