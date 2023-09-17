package myGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Finish extends MouseAdapter {
    private final Image MainMenu = new ImageIcon("resources/bg.jpg").getImage();
    private final Handler handler;
    private Game game;

    public Finish(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Exit
        if (mouseOver(mx, my, 500, 600, 500, 150) && Game.gameState == Game.STATE.Finish) {
            System.exit(1);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {
    }

    public void render(Graphics g) {

        Font fnt = new Font("arial", Font.BOLD, 100);
        Font fnt2 = new Font("arial", Font.BOLD, 50);
        g.drawImage(MainMenu, 0, 0, 1600, 900, null);
        g.setColor(Color.WHITE);

        g.setFont(fnt);
        if(game.getHud().HEALTH <= 0){
            g.drawString("Game Over", 480, 250);
            g.setFont(fnt2);
            g.drawString("Your Score Is: " +Game.SCORE, 480, 400);
        }

        g.setFont(fnt2);
        g.drawString("Exit", 700, 690);
        g.drawRect(500, 600, 500, 150);//Exit
    }
}
