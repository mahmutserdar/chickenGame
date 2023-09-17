package myGame;

import java.awt.*;

public class HUD {


    public int HEALTH = 200;
    LevelListener levelListener;

    public HUD(LevelListener levelListener) {
        this.levelListener = levelListener;
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 200);
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 15, 200, 32);
        g.setColor(Color.green);
        g.fillRect(0, 15, HEALTH, 32);
        g.setColor(Color.WHITE);
        g.drawRect(0, 15, 200, 32);

        Font fnt = new Font("arial", Font.BOLD, 20);
        g.setFont(fnt);
        g.drawString("SCORE : " + Game.SCORE, 20, 80);
        g.drawString("Level : " + levelListener.getCurrentLevel(), 20, 120);
    }
}
