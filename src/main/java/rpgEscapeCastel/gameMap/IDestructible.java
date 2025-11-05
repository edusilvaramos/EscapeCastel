package rpgEscapeCastel.gameMap;

public interface IDestructible {

    public void takeDamage(int amount);
    public boolean isDestroyed();
    public String getFileName();
    public int getPower();
    public int getLife();

}
