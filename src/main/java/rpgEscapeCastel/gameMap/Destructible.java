package rpgEscapeCastel.gameMap;

public interface Destructible {

    void takeDamage(int amount);

    boolean isDestroyed();
}
