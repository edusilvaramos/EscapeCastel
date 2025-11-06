package rpgEscapeCastele.gameMap;

public final class EmptyCell implements IPlace {

    public EmptyCell() {
    }
    
    @Override
    public String symbolPlace() {
        return " ";
    }

    @Override
    public boolean isFreePlace() {
        return true;
    }

    @Override
    public String getFileName() {
        return "empty.txt";
    }

    @Override
    public IDestructible asDestructible() {
        return null;
    }

}
