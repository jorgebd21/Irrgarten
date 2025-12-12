package irrgarten;

import irrgarten.UI.GUI;
import irrgarten.controller.Controller; // Importamos la clase GUI

public class irrgarten {
    static public void main(String[] args) {
        // Usa la nueva clase GUI
        Controller controller = new Controller(new Game(2), new GUI()); 
        controller.play();
    }
}