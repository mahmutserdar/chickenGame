package myGame;

import java.awt.*;
import java.io.IOException;

import static myGame.Player.getImage;

public class Cats extends GameObject{

    public Cats(int x, int y, ID id) throws IOException {
        super(x, y, id);
    }

    public void tick() {
        x -= velX;
        y -= velY;
    }

    String imagePath = "resources/cat2.png";
    Image imageSmallCat = getImage(imagePath, 100, 100);
    public void render(Graphics g) throws IOException {
        Color c = new Color(0f,0f,0f,.0f );
        g.setColor(c);
        g.drawImage(imageSmallCat, x,y, null);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 100);
    }

}
