package myGame;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(int width, int height, String title, Game game){

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.setSize(1600,900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
}
