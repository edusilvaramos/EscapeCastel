package rpgEscapeCastel.gameMap;
public class Monster implements Destructible {
    private final String name;
    private int life;
    private final int defense;
    public Monster(String name, int life, int defense) {
        this.name = name; this.life = life; this.defense = defense;
    }
    @Override public void takeDamage(int amount) {
        int effective = Math.max(0, amount - defense);
        life -= effective;
    }
    @Override public boolean isDestroyed() { return life <= 0; }
}
