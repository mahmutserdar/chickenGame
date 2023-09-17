package myGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class Player extends GameObject {
    Handler handler;
    LevelListener levelListener;

    Game game;
    Robot r = new Robot();
    public Player(int x, int y, ID id, Handler handler, LevelListener levelListener, Game game) throws AWTException {
        super(x, y, id);
        this.handler = handler;
        this.levelListener = levelListener;
        this.game = game;
    }
    public boolean soundBool = true;

    public Rectangle getBounds() {
        return new Rectangle(x, y, 70, 70);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, 1520);
        y = Game.clamp(y, 0, 800);
        control();
    }
    public void control() {

        LinkedList<GameObject> Cats = new LinkedList<>();
        LinkedList<GameObject> bigEnemies = new LinkedList<>();
        LinkedList<GameObject> bullets = new LinkedList<>();
        LinkedList<GameObject> smallEnemies = new LinkedList<>();
        for (GameObject temp : handler.objects) {
            if (temp.getId().isBullet()) bullets.add(temp);
            else if (temp.getId().isCats()) Cats.add(temp);
            else if (temp.getId().isBigEnemy()) bigEnemies.add(temp);
            else if (temp.getId().isSmallEnemy()) smallEnemies.add(temp);
        }

        for (GameObject cat : Cats) {
            for (GameObject bullet : bullets) {
                if (cat.getBounds().intersects(bullet.getBounds())) {
                    handler.removeObject(cat);
                    handler.removeObject(bullet);
                    Game.playSound("hit",0);

                }
            }
        }
        for (GameObject bigEnemy : bigEnemies) {
            for (GameObject bullet : bullets) {
                if (bigEnemy.getBounds().intersects(bullet.getBounds())) {
                    handler.removeObject(bigEnemy);
                    handler.removeObject(bullet);
                    Game.SCORE = Game.SCORE + 10;
                    Game.playSound("hit",0);
                }
            }
        }
        for (GameObject smallEnemy : smallEnemies) {
            for (GameObject bullet : bullets) {
                if (smallEnemy.getBounds().intersects(bullet.getBounds())) {
                    handler.removeObject(smallEnemy);
                    handler.removeObject(bullet);
                    Game.SCORE = Game.SCORE + 5;
                    Game.playSound("hit",0);
                }
            }
        }
        if (Cats.isEmpty() && bigEnemies.isEmpty() && smallEnemies.isEmpty()) {
            for (GameObject bullet : bullets) handler.removeObject(bullet);
            levelListener.onLevelIncreased(handler);
            r.keyPress(KeyEvent.VK_E);
            r.keyRelease(KeyEvent.VK_E);
        }
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getId() == ID.Cats || tempObject.getId() == ID.bigEnemy || tempObject.getId() == ID.smallEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    healthDecrease(tempObject);
                }
                if (tempObject.getY() < 0) {
                    healthDecrease(tempObject);
                }
            }
        }
    }

    private void healthDecrease(GameObject tempObject) {
        game.getHud().HEALTH = game.getHud().HEALTH - 20;
        handler.removeObject(tempObject);
        if(soundBool) Game.playSound("touch",0);
        if (game.getHud().HEALTH <= 0) {
            Menu.screen = "Finish";
            soundBool = false;
            Game.gameState = Game.STATE.Finish;
        }
    }

    String imagePath = "resources/chicken.png";
    Image image = getImage(imagePath, 70, 70);

    public void render(Graphics g) {
        Color c = new Color(0f, 0f, 0f, .0f);
        g.setColor(c);
        g.drawImage(image, x, y, null);
    }

    public static Image getImage(String path, int width, int height) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

}
