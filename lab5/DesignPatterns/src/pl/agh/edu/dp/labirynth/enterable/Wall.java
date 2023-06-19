package pl.agh.edu.dp.labirynth.enterable;

public class Wall implements WallLike, Enterable {
    public Wall(){

    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    public Room enter(Room startingRoom){
        return null;
    }

    @Override
    public String toString() {
        return "wall";
    }
}
