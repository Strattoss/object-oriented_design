package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Maze;
import pl.agh.edu.dp.labirynth.Position;
import pl.agh.edu.dp.labirynth.enterable.Enterable;
import pl.agh.edu.dp.labirynth.enterable.Room;

public class Player {
    private Room room;

    public Player(Room room) {
        this.room = room;
    }

    public void move(Direction direction) {
        Enterable roomSide = this.room.getSide(direction);
        if (!roomSide.canEnter()) {
            System.out.println("Cannot go " + direction + ", there is a " + roomSide + ", which cannot be entered.");
            return;
        }

        this.room = roomSide.enter(this.room);
        System.out.println("You've entered: " + this.room);
    }

    public String drawSurrounding() {
        return "\t" + "N=" + this.room.getSide(Direction.NORTH) + "\t\n" +
                "W=" + this.room.getSide(Direction.WEST) + "\tX\t" + "E=" + this.room.getSide(Direction.EAST) + "\n" +
                "\t" + "S=" + this.room.getSide(Direction.SOUTH) + "\t";
    }
}
