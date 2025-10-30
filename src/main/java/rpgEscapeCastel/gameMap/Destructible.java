package rpgEscapeCastel.gameMap;

public interface Destructible {

    public void takeDamage(int amount);
    public boolean isDestroyed();
    public String getFileName();
    // fight
    public int getPower();
    public int getLife();

}
