package pl.agh.edu.dp.labirynth.enterable;

import pl.agh.edu.dp.labirynth.PlayerBombedException;
import pl.agh.edu.dp.labirynth.Position;

/**
 * A player can enter a bombed room only fixed amount of times (3 times by default).
 * Otherwise, player will be bombed.
 */
public class BombedRoom extends Room{
    int entriesLeft = 3;

    public BombedRoom(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "bombed room {" + entriesLeft + "}";
    }

    @Override
    public Room enter(Room startingRoom) {
        if (this.entriesLeft <= 0) {
            throw new PlayerBombedException();
        }

        entriesLeft--;

        return super.enter(startingRoom);
    }
}
