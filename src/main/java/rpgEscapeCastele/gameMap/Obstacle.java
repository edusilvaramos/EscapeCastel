package rpgEscapeCastele.gameMap;

public class Obstacle implements IDestructible, IPlace{

    private String obstacleType = "obstacle.txt";
    private int power;

    public Obstacle() {
        this.obstacleType = "obstacle.txt";
        this.power = 60;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount < 0) {
            return;
        }
        power -= amount;
        if (power < 0) {
            power = 0;
        }
    }

    @Override
    public String symbolPlace() {
        return "   ";
    }

    @Override
    public String toString() {
        return "Obstacle{" + obstacleType + ", power=" + power + "}";
    }

    @Override
    public boolean isDestroyed() {
        return power <= 0;
    }
    @Override
    public String getFileName() {
        return obstacleType;
    }
    @Override
    public int getPower() {
        return 0;
    }
    @Override
    public int getLife() {
        return power;
    }
    @Override
    public boolean isFreePlace() {
        return false;   
    }

       @Override
    public IDestructible asDestructible() {
        return this;
    }


}
