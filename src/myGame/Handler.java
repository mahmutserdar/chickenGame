package myGame;

import java.awt.*;
import java.util.LinkedList;

public class Handler  {

    LinkedList<GameObject> objects = new LinkedList<>();
    public void tick(){
        for(int i = 0; i<objects.size(); i++){
            GameObject tempObject = objects.get(i);
                tempObject.tick();
        }

    }

    public void render(Graphics g){
        for(int i = 0; i<objects.size(); i++){
            GameObject tempObject = objects.get(i);
            try {
                tempObject.render(g);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
}
