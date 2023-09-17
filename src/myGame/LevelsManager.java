package myGame;

public class LevelsManager implements LevelListener {
    int level = 0;
    public void increaseLevel(Handler handler) {
        level += 1;
        for (int i = 0; i < 3 * level; i++) {
            int y = Game.getRandomNumber(900,1300);
            try {
                handler.addObject(new Cats(Game.getRandomNumber(20, 1450), y, ID.Cats));
                handler.addObject(new bigEnemy(Game.getRandomNumber(20, 1450), y, ID.bigEnemy));
                handler.addObject(new smallEnemy(Game.getRandomNumber(20, 1450), y,ID.smallEnemy));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void onLevelIncreased(Handler handler) {
        this.increaseLevel(handler);
    }

    @Override
    public int getCurrentLevel() {
        return (int) level;
    }

}

interface LevelListener {
    public void onLevelIncreased(Handler handler);

    public int getCurrentLevel();
}
