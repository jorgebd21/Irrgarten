package irrgarten.controller;

import irrgarten.Directions;
import irrgarten.Game;
import irrgarten.UI.GUI;

public class Controller {

    private Game game;
    private GUI view;

    public Controller(Game game, GUI view) {
        this.game = game;
        this.view = view;
    }

    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState());
            Directions direction = view.nextMove();
            endOfGame = game.nextStep(direction);
        }
        view.showGame(game.getGameState());
    }

}
