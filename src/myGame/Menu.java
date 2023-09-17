package myGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private final Image MainMenu = new ImageIcon("resources/bg.jpg").getImage();
    private final Handler handler;
    private HUD hud;
    private Player player;
    public static String screen = "Menu";
    private final LevelsManager levelsManager = new LevelsManager();

    public Menu(Game game, Handler handler) throws AWTException {
        this.handler = handler;
        this.hud = game.getHud();
        player = new Player(900, 50, ID.mainPlayer, handler, levelsManager, game);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Play
        if (mouseOver(mx, my, 500, 200, 500, 150) && Game.gameState != Game.STATE.Game
                && Game.gameState != Game.STATE.Finish && Game.gameState != Game.STATE.Info) {
            handler.addObject(new Background(0,0,ID.background));
            Game.gameState = Game.STATE.Game;
            Menu.screen = "Game";
            handler.addObject(player);

//            levelsManager.increaseLevel(handler);
        }
        // Info
        if (mouseOver(mx, my, 500, 400, 500, 150) && Game.gameState != Game.STATE.Game
                && Game.gameState != Game.STATE.Finish) {
            Game.gameState = Game.STATE.Info;
        }

        // Exit
        if (mouseOver(mx, my, 500, 600, 500, 150) && Game.gameState != Game.STATE.Game
                && Game.gameState != Game.STATE.Finish && Game.gameState != Game.STATE.Info ) {
            System.exit(1);
        }

        // Back
        if(Game.gameState == Game.STATE.Info) {
            if (mouseOver(mx, my, 500, 600, 500, 150))
                Game.gameState = Game.STATE.Menu;
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

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public void render(Graphics g) {

        if (Game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", Font.BOLD, 75);
            Font fnt2 = new Font("arial", Font.BOLD, 50);

            g.drawImage(MainMenu, 0, 0, 1600, 900, null);
            g.setColor(Color.WHITE);

            g.setFont(fnt);
            g.drawString("MENU", 650, 150);

            g.setFont(fnt2);
            g.drawString("Play",700, 290);
            g.drawString("Info", 700, 490);
            g.drawString("Exit", 700, 690);

            g.drawRect(500, 200, 500, 150);//Play
            g.drawRect(500, 400, 500, 150);//Info
            g.drawRect(500, 600, 500, 150);//Exit
        } else if (Game.gameState == Game.STATE.Info) {
            Font fnt2 = new Font("arial", Font.BOLD, 50);
            Font fnt3 = new Font("arial", Font.BOLD, 20);
            g.drawImage(MainMenu, 0, 0, 1600, 900, null);
            g.setFont(fnt3);
            g.setColor(Color.WHITE);
            g.drawString("In this game, the player will be the chicken (as the Instructions says),", 100, 250);
            g.drawString("and you have to kill every cat and fry pan that appears in your way. However,", 100, 310);
            g.drawString("if you touch or miss shooting a cat or fry pan, your health will go down by 10%.", 100, 370);
            g.drawString("If you shoot any of these fry pans, you will earn 10 points", 100, 430);
            g.drawString("To play this game all you have to do is to press WASD keys to move and SPACE to shoot", 100, 490);
            g.drawString("Good luck, and don't die, the world's life depends on YOU!.", 100, 550);
            g.drawRect(500, 600, 500, 150);// Back
            g.setFont(fnt2);
            g.drawString("Back", 700, 690);
        }
    }

    public LevelListener getLevelsManager() {
        return levelsManager;
    }
}
