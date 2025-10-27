package irrgarten;

import java.util.ArrayList;

public class Game {
    static private int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;

    private ArrayList<Player> players;
    private Labyrinth labyrinth;
    private ArrayList<Monster> monsters;

    public Game(int nplayers){
        this.currentPlayerIndex = nplayers-1;
        this.log = "";
        this.players = new ArrayList<Player>(nplayers);
        this.labyrinth = new Labyrinth(10, 10, 9, 9);
        this.monsters = new ArrayList<Monster>();
        configureLabyrinth();
    }
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    public boolean nextStep(Directions preferredDirections){
        throw new UnsupportedOperationException();
    }
    public GameState getGameState(){
        return new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log);
    }
    private void configureLabyrinth(){
        //Necesito completarlo
    }
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = players.get(currentPlayerIndex).getRow();
        int currentCol = players.get(currentPlayerIndex).getCol();

        Directions[] validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = players.get(currentPlayerIndex).move(preferredDirection, validMoves);
        
        return output;
    }
    private GameState combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    private void manageReward(GameState winner){

    }
    private void manageResurrection(){

    }
    private void logPlayerWon(){
        log += players.get(currentPlayerIndex).getName() + " ha ganado el combate!\n";
    }
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate!\n";
    }
    private void logResurrected(){
        log += players.get(currentPlayerIndex).getName() + " ha resucitado!\n";
    }
    private void logPlayerSkipTurn(){
        log += players.get(currentPlayerIndex).getName() + " se salta el turno!\n";
    }
    private void logPlayerNoOrders(){
        log += players.get(currentPlayerIndex).getName() + " no tiene ordenes!\n";
    }
    private void logNoMonster(){
        log += "No hay monstruo en la nueva posicion!\n";
    }
    private void logRound(int round, int max){
        log += "---- Ronda " + round + " de " + max + " ----\n";
    }
}