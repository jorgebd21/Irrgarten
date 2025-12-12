package irrgarten;

import irrgarten.UI.GraphicalUI;
import irrgarten.controller.Controller;

public class irrgarten {
    static public void main(String[] args) {
        
        Game game = new Game(2);
        GraphicalUI view = new GraphicalUI();

        Controller controller = new Controller(game, view);
        controller.play();
    }
}