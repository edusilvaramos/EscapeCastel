package rpgEscapeCastel.gameMap;

public class Obstacle implements Destructible {

    private final String type;
    private int life;

    public Obstacle(String type, int life) {
        this.type = type;
        this.life = life;
    }

    @Override
    public void takeDamage(int amount) {
        life -= amount;
    }

    @Override
    public boolean isDestroyed() {
        return life <= 0;
    }
}
