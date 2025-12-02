package irrgarten;

import irrgarten.controller.Controller;
import irrgarten.UI.TextUI;

public class irrgarten {
    static public void main(String[] args) {
        Controller controller = new Controller(new Game(2), new TextUI());
        controller.play();
    }
}