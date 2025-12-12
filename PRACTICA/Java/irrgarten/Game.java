package irrgarten;

import java.util.ArrayList;

public class Game {
    static private final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;

    private final int nRow = 10;
    private final int nCol = 10;
    private final int rowPosE = 8;
    private final int colPosE = 8;

    private ArrayList<Player> players;
    private Labyrinth labyrinth;
    private ArrayList<Monster> monsters;
    private Player currentPlayer;

    public Game(int nplayers) {
        this.currentPlayerIndex = 0;
        this.log = "";
        this.players = new ArrayList<>(nplayers);
        this.monsters = new ArrayList<>();
        this.labyrinth = new Labyrinth(nRow, nCol, rowPosE, colPosE);

        for (int i = 0; i < nplayers; i++) {
            Player p = new Player((char) (i + '1'), Dice.randomIntelligence(), Dice.randomStrength());
            this.players.add(p);
        }
        currentPlayer = players.get(currentPlayerIndex);

        configureLabyrinth();
    }

    public boolean finished() {
        return labyrinth.haveAWinner();
    }

    public boolean nextStep(Directions preferredDirections) {
        log = "";
        boolean dead = currentPlayer.dead();

        if (!dead) {
            Directions direction = actualDirection(preferredDirections);

            if (direction != preferredDirections) {
                logPlayerNoOrders();
            }

            Monster monster = labyrinth.putPlayer(direction, currentPlayer);

            if (monster == null) {
                logNoMonster();
            } else {
                GameCharacter winner = combat(monster);
                manageReward(winner, monster);
            }

        } else {
            labyrinth.removePlayer(currentPlayer);
            manageResurrection();
        }

        boolean endGame = finished();
        if (!endGame) {
            nextPlayer();
        }
        return endGame;
    }

    public GameState getGameState() {
        String playerS = "";
        for (int i = 0; i < players.size(); i++) {
            playerS += players.get(i).toString();
            playerS += "---------------------------------------------------------\n";
        }

        String monsterS = "";
        for (int i = 0; i < monsters.size(); i++) {
            monsterS += monsters.get(i).toString();
            monsterS += "---------------------------------------------------------\n";
        }

        return new GameState(labyrinth.toString(), playerS, monsterS, currentPlayerIndex,
                finished(), log);
    }

    private void configureLabyrinth() {
        labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, nRow);
        labyrinth.addBlock(Orientation.HORIZONTAL, nRow - 1, 0, nRow);
        labyrinth.addBlock(Orientation.VERTICAL, 1, 0, nCol - 2);
        labyrinth.addBlock(Orientation.VERTICAL, 1, nCol - 1, nCol - 2);

        labyrinth.spreadPlayers(players);

        Monster m1 = new Monster("A", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(m1);
        int i[] = labyrinth.randomEmptyPos();
        labyrinth.addMonster(i[0], i[1], m1);

        Monster m2 = new Monster("B", Dice.randomStrength(), Dice.randomIntelligence());
        monsters.add(m2);
        i = labyrinth.randomEmptyPos();
        labyrinth.addMonster(i[0], i[1], m2);

        Monster m3 = new Monster("C", Dice.randomStrength(), Dice.randomIntelligence());
        monsters.add(m3);
        i = labyrinth.randomEmptyPos();
        labyrinth.addMonster(i[0], i[1], m3);
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    private Directions actualDirection(Directions preferredDirection) {
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();

        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);

        return output;
    }

    private GameCharacter combat(Monster monster) {
        int round = 0;
        GameCharacter winner = GameCharacter.PLAYER;

        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        while (!lose && round < MAX_ROUNDS) {
            winner = GameCharacter.MONSTER;
            round++;

            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);

            if (!lose) {
                winner = GameCharacter.PLAYER;
                playerAttack = currentPlayer.attack();
                lose = monster.defend(playerAttack);
            }
        }

        logRound(round, MAX_ROUNDS);
        return winner;
    }

    private void manageReward(GameCharacter winner, Monster monster) {
        if (winner == GameCharacter.PLAYER) {
            currentPlayer.receivedReward();
            logPlayerWon();
            if (monster.dead()) {
                labyrinth.removeMonster(monster);
                monsters.remove(monster);
            }
        } else {
            logMonsterWon();
        }
    }

    private void manageResurrection() {
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect) {
            currentPlayer.resurrect();
            currentPlayer = new FuzzyPlayer(currentPlayer);
            players.set(currentPlayerIndex, currentPlayer);
            logResurrected();
        } else {
            logPlayerSkipTurn();
        }
    }

    private void logPlayerWon() {
        log += currentPlayer.getNumber() + " ha ganado el combate!\n";
    }

    private void logMonsterWon() {
        log += "El monstruo ha ganado el combate!\n";
    }

    private void logResurrected() {
        log += currentPlayer.getNumber() + " ha resucitado!\n";
    }

    private void logPlayerSkipTurn() {
        log += currentPlayer.getNumber() + " se salta el turno!\n";
    }

    private void logPlayerNoOrders() {
        log += currentPlayer.getNumber() + " no tiene ordenes!\n";
    }

    private void logNoMonster() {
        log += "El jugador se ha movido a una celda vacia o no se ha podido mover\n";
    }

    private void logRound(int round, int max) {
        log += "---- Ronda " + round + " de " + max + " ----\n";
    }
}