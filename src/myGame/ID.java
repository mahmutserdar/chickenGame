package myGame;

public enum ID {
    Cats(),
    bigEnemy(),
    smallEnemy(),
    mainPlayer(),
    background(),
    Bullet();

    public boolean isBullet() {
        return this == Bullet;
    }

    public boolean isCats() {
        return this == Cats;
    }
    public boolean isBigEnemy() {
        return this == bigEnemy;
    }

    public boolean isSmallEnemy() {
        return this == smallEnemy;
    }

}
