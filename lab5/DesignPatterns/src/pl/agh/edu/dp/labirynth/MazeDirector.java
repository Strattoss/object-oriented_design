package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.builder.CountingMazeBuilder;
import pl.agh.edu.dp.labirynth.builder.MazeBuilder;
import pl.agh.edu.dp.labirynth.builder.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.factory.MazeFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MazeDirector {
    public Maze createStandardMaze(MazeFactory mazeFactory) {
        StandardMazeBuilder standardMazeBuilder = new StandardMazeBuilder(mazeFactory);
        runMazeBuilder(standardMazeBuilder);
        return standardMazeBuilder.getResult();
    }

    public Map<String, Integer> countMazeElements() {
        CountingMazeBuilder countingMazeBuilder = new CountingMazeBuilder();
        runMazeBuilder(countingMazeBuilder);
        return countingMazeBuilder.getCounts();
    }

    private void runMazeBuilder(MazeBuilder mazeBuilder) {
        // 3 # RRR
        // 2 # R R
        // 1 # RRR
        // 0 # RR
        // ### 012
        List<Position> positions = new ArrayList<>(Arrays.asList(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(1, 0),
                new Position(1, 1),
                new Position(1, 3),
                new Position(2, 1),
                new Position(2, 2),
                new Position(2, 3)
        ));

        for (Position position: positions) {
            mazeBuilder.addRoomSurroundedByWalls(position);
        }

        mazeBuilder.addDoor(positions.get(0), Direction.NORTH);
        mazeBuilder.connectRooms(positions.get(0), Direction.EAST);

        mazeBuilder.addDoor(positions.get(5), Direction.SOUTH);
        mazeBuilder.addDoor(positions.get(5), Direction.EAST);
        mazeBuilder.connectRooms(positions.get(5), Direction.WEST);

        mazeBuilder.addDoor(positions.get(2), Direction.SOUTH);
        mazeBuilder.addDoor(positions.get(2), Direction.NORTH);

        mazeBuilder.addDoor(positions.get(8), Direction.SOUTH);
        mazeBuilder.addDoor(positions.get(8), Direction.NORTH);

        mazeBuilder.connectRooms(positions.get(6), Direction.EAST);
        mazeBuilder.connectRooms(positions.get(6), Direction.WEST);
    }
}
