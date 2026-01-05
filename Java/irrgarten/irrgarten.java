package irrgarten;

import irrgarten.UI.GraphicUI;
import irrgarten.controller.Controller;

public class irrgarten {
    static public void main(String[] args) {
        
        Game game = new Game(2);
        GraphicUI view = new GraphicUI();

        Controller controller = new Controller(game, view);
        controller.play();
    }
}