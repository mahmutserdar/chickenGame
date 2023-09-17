package myGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class keyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    Game game;
    public keyInput(Handler handler){
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    private boolean canShoot = true;
    public void keyPressed(KeyEvent e) {
        int x_mainPlayer = 0;
        int y_mainPlayer = 0;
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.mainPlayer) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-10);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(10);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(10);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-10);
                    keyDown[3] = true;
                }
                x_mainPlayer = tempObject.getX();
                y_mainPlayer = tempObject.getY();

                if (key == KeyEvent.VK_SPACE && Game.gameState != Game.STATE.Finish && canShoot) {
                    handler.addObject(new Egg(x_mainPlayer + 20, y_mainPlayer + 40, ID.Bullet, handler));
                    canShoot = false;
                    Game.playSound("shootSound",0);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            canShoot = true;
                        }
                    }, 100);
                }

            }


            if(tempObject.getId() == ID.Cats){
                    tempObject.setVelY(2);
            }
            if(tempObject.getId() == ID.bigEnemy){
                    tempObject.setVelY(3);
            }
            if(tempObject.getId() == ID.smallEnemy){
                    tempObject.setVelY(3);
            }
            if(tempObject.getId() == ID.Bullet){
                    tempObject.setVelY(12);
            }

            if(tempObject.getId() == ID.background)
                tempObject.setVelY(1);

        }

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.mainPlayer) {
                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_D) keyDown[2] = false;
                if (key == KeyEvent.VK_A) keyDown[3] = false;

                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }

        }
    }

}

