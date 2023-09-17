package myGame;

import java.awt.*;

import static myGame.Player.getImage;

public class Egg extends GameObject{
    public Egg(int x, int y, ID id, Handler handler) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        y += velY;
        x += velX;
    }

    String imagePath = "resources/egg.png";
    Image image = getImage(imagePath, 25, 32);

    @Override
    public void render(Graphics g) {
        Color c = new Color(0f,0f,0f,.0f );
        g.setColor(c);
        g.drawImage(image, x,y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 25, 32);
    }

}
