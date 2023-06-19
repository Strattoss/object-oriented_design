package pl.agh.edu.dp.labirynth.enterable;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Position;

import java.util.EnumMap;
import java.util.Map;

public class Room implements Enterable
{
    private final Map<Direction, Enterable> sides;
    private final Position position;

    public Room(Position position) {
        this.position = position;
        this.sides = new EnumMap<>(Direction.class);
    }

    public Enterable getSide(Direction direction){
        return this.sides.get(direction);
    }

    public void setSide(Direction direction, Enterable enterable){
        this.sides.put(direction, enterable);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean canEnter() {
        return true;
    }

    @Override
    public Room enter(Room startingRoom) {
        if (canEnter()) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "room";
    }
}
