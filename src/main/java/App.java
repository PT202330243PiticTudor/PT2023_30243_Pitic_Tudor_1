import gui.Controller;
import gui.Model;
import gui.View;

import java.awt.*;

public class App {
    public static void main( String[] args ) {
        EventQueue.invokeLater(() -> {
            try {
                Model theModel = new Model();
                View theView = new View();
                new Controller(theView, theModel);
                theView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}