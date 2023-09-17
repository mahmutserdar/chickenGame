package myGame;

import java.awt.*;
import java.io.IOException;

import static myGame.Player.getImage;
public class smallEnemy extends GameObject{
    public static boolean isSmallEnemy = true;
    public smallEnemy(int x, int y, ID id) throws IOException {
        super(x, y, id);
    }


    String imagePath = "resources/target.png";
    Image imageBigCat = getImage(imagePath, 75, 75);

    public void tick() {
        x -= velX;
        y -= velY;
    }

    public void render(Graphics g) throws IOException {
        Color c = new Color(0f,0f,0f,.0f );
        g.setColor(c);
        g.drawImage(imageBigCat, x,y, null);
    }

    public Rectangle getBounds() {
        isSmallEnemy = false;
        return new Rectangle(x, y, 75, 75);
    }
}
