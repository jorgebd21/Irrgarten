package irrgarten;

public class Game {
    static private int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;

    private Player[] players;
    private Labyrinth labyrinth;
    private Monster[] monsters;

    public Game(int nplayers){

    }
    public boolean finished(){
        throw new UnsupportedOperationException();
    }
    public boolean nextStep(Directions preferredDirections){
        throw new UnsupportedOperationException();
    }
    public GameState getGameState(){
        throw new UnsupportedOperationException();
    }
    private void configureLabyrinth(){

    }
    private void nextPlayer(){

    }
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    private GameState combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    private void manageReward(GameState winner){

    }
    private void manageResurrection(){

    }
    private void logPlayerWon(){

    }
    private void logMonsterWon(){

    }
    private void Resurrected(){

    }
    private void logPlayerSkipTurn(){

    }
    private void logPlayerNoOrders(){

    }
    private void logNoMonster(){

    }
    private void logRound(int round, int max){

    }
}