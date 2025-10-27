package irrgarten;

import java.util.ArrayList;

public class Game {
    static private int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;

    private ArrayList<Player> players;
    private Labyrinth labyrinth;
    private ArrayList<Monster> monsters;
    private Player currentPlayer;

    public Game(int nplayers){
        this.currentPlayerIndex = nplayers-1;
        this.log = "";
        this.players = new ArrayList<Player>(nplayers);
        this.labyrinth = new Labyrinth(10, 10, 9, 9);
        this.monsters = new ArrayList<Monster>();
        this.currentPlayer = players.get(currentPlayerIndex);
        configureLabyrinth();
    }
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    public boolean nextStep(Directions preferredDirections){
        log  = "";
        boolean dead = currentPlayer.dead();

        if(!dead){
            Directions direction = actualDirection(preferredDirections);

            if(direction != preferredDirections){
                logPlayerNoOrders();
            }

            Monster monster = labyrinth.putPlayer(direction, currentPlayer);

            if(monster == null){
                logNoMonster();
            }
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }

        }else{
            manageResurrection();
        }

        boolean endGame = finished();
        if(!endGame){
            nextPlayer();
        }
        return endGame;
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
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();

        Directions[] validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);

        return output;
    }
    private GameCharacter combat(Monster monster){
        int round = 0;
        GameCharacter winner = GameCharacter.PLAYER;

        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        while(!lose && round < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            round++;

            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);

            if(!lose){
                winner = GameCharacter.PLAYER;
                playerAttack = currentPlayer.attack();
                lose = monster.defend(playerAttack);
            }
        }

        logRound(round, MAX_ROUNDS);
        return winner;
    }
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receivedReward();
            logPlayerWon();
        }else{
            logMonsterWon();
        }
    }
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else{
            logPlayerSkipTurn();
        }
    }
    private void logPlayerWon(){
        log += currentPlayer.getName() + " ha ganado el combate!\n";
    }
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate!\n";
    }
    private void logResurrected(){
        log += currentPlayer.getName() + " ha resucitado!\n";
    }
    private void logPlayerSkipTurn(){
        log += currentPlayer.getName() + " se salta el turno!\n";
    }
    private void logPlayerNoOrders(){
        log += currentPlayer.getName() + " no tiene ordenes!\n";
    }
    private void logNoMonster(){
        log += "No hay monstruo en la nueva posicion!\n";
    }
    private void logRound(int round, int max){
        log += "---- Ronda " + round + " de " + max + " ----\n";
    }
}