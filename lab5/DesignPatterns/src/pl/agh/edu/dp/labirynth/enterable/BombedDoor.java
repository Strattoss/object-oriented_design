package pl.agh.edu.dp.labirynth.enterable;

public class BombedDoor extends Door{
    public BombedDoor(Room r1, Room r2) {
        super(r1, r2);
    }

    @Override
    public String toString() {
        return "bombed door";
    }
}
