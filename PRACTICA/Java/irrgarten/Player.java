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
    public void setPos(int row, int col) {

    }
    public boolean dead() {
        throw new UnsupportedOperationException();
    }
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }
    public float attack(){
        throw new UnsupportedOperationException();
    }
    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    public void receivedReward(){

    }
    public String toString(){
        throw new UnsupportedOperationException();
    }

    private void receiveWeapon(Weapon w){

    }
    private void receiveShield(Shield s){

    }
    private Weapon newWeapon(){
        throw new UnsupportedOperationException();
    }
    private Shield newShield(){
        throw new UnsupportedOperationException();
    }
    private float sumWeapons(){
        throw new UnsupportedOperationException();
    }
    private float sumShields(){
        throw new UnsupportedOperationException();
    }
    private float defensiveEnergy(){
        throw new UnsupportedOperationException();
    }
    private boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    private void resetHits(){
        
    }
    private void gotWounded(){
        
    }
    private void incConsecutiveHits(){
        
    }
}
