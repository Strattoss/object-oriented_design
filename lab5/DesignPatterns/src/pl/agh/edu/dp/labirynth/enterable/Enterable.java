package pl.agh.edu.dp.labirynth.enterable;

import pl.agh.edu.dp.labirynth.Position;

public interface Enterable {
    public boolean canEnter();
    public Room enter(Room startingRoom);
}
