package rpgEscapeCastele.gameMap;

public interface IPlace {

    public String symbolPlace();

    public boolean isFreePlace();

    public String getFileName();

    public IDestructible asDestructible();

}