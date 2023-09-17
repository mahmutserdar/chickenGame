package myGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 650, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
    private Handler handler;

    private Random r;
    private HUD hud;

    private Finish finish;
    private Menu menu;

    public HUD getHud() {
        return hud;
    }

    public enum STATE {
        Menu,
        Finish,
        Game, Info;
    }
    public static int SCORE = 0;
    public static STATE gameState = STATE.Menu;
    public Game() throws IOException, AWTException {
        handler = new Handler();
        menu = new Menu(this,handler);
        hud = new HUD(menu.getLevelsManager());
        finish= new Finish(this,handler);
        this.addKeyListener(new keyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(finish);

        new Window(WIDTH,HEIGHT,"GAME",this);
        r = new Random();
    }
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta = delta + (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer = timer + 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        if(gameState == STATE.Game){
            Menu.screen = "Game";
            hud.tick();
        }else if(gameState == STATE.Menu){
            Menu.screen = "Menu";
            menu.tick();
        }else if(gameState == STATE.Finish){
            Menu.screen = "Finish";
            finish.tick();
        }
    }
    private final Image gameBg = new ImageIcon("resources/ngGame.png").getImage();
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, (int)size.getWidth(), (int)size.getWidth());
//        g.drawImage(gameBg, 0, 0, (int)size.getWidth(), (int)size.getHeight(), null);
        handler.render(g);

        if(gameState == STATE.Game){
            Menu.screen = "Game";
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Info){
            Menu.screen = "MenuUU";
            menu.render(g);
        }else if(gameState == STATE.Finish){
            Menu.screen = "Finish";
            finish.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static void playSound(String s, int loopTimes) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/" + s +".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(loopTimes);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static int clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else return var;
    }

    public static void main(String[] args) throws IOException, AWTException {
            new Game();
            Game.playSound("music",50);
    }
}
