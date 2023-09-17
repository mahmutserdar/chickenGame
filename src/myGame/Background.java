package myGame;

import java.awt.*;
import java.io.IOException;

import static myGame.Player.getImage;

public class Background extends GameObject{
    public Background(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x -= velX;
        y -= velY;
    }

    String imagePath = "resources/finalBG.jpg";
    Image imageBigCat = getImage(imagePath, 1600, 7391);
    @Override
    public void render(Graphics g) throws IOException {
        Color c = new Color(0f,0f,0f,.0f );
        g.setColor(c);
        g.drawImage(imageBigCat, x,y, null);
    }
    @Override
    public Rectangle getBounds() {
        return null;
    }
}
