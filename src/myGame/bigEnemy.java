package myGame;

import java.awt.*;
import java.io.IOException;

import static myGame.Player.getImage;
public class bigEnemy extends GameObject{
    public static boolean isBigEnemy = true;
    public bigEnemy(int x, int y, ID id) throws IOException {
        super(x, y, id);
    }
    public void tick() {
        x -= velX;
        y -= velY;
    }
    String imagePath = "resources/target.png";
    Image imageBigCat = getImage(imagePath, 150, 150);
    public void render(Graphics g) throws IOException {
        Color c = new Color(0f,0f,0f,.0f );
        g.setColor(c);
        g.drawImage(imageBigCat, x,y, null);
    }
    public Rectangle getBounds() {
        isBigEnemy = false;
        return new Rectangle(x, y, 150, 150);
    }

}
