package rpgEscapeCastel.gameMap;

public class Obstacle implements Destructible, InterfacePlace {

    private final String type;
    private int power;

    public Obstacle(String type, int power) {
        this.type = type;
        this.power = power;
    }

    @Override
    public void takeDamage(int amount) {
        power -= amount;
    }

    @Override
    public boolean isDestroyed() {
        return power <= 0;
    }

    @Override
    public String symbolPlace() {
        return " O ";
    }
}
