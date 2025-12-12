package irrgarten.controller;

import irrgarten.Directions;
import irrgarten.Game;
import irrgarten.UI.GraphicUI;
import javax.swing.SwingUtilities;

public class Controller {

    private Game game;
    private GraphicUI view;

    public Controller(Game game, GraphicUI view) {
        this.game = game;
        this.view = view;
    }

    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            SwingUtilities.invokeLater(() -> view.showGame(game.getGameState()));
            Directions direction = view.nextMove();
            endOfGame = game.nextStep(direction);
        }
        SwingUtilities.invokeLater(() -> view.showGame(game.getGameState()));
    }

}
