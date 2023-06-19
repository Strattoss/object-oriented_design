package pl.agh.edu.dp.labirynth;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public Position getUnitVector() {
        return switch (this) {
            case NORTH -> new Position(0, 1);
            case SOUTH -> new Position(0, -1);
            case EAST -> new Position(1, 0);
            case WEST -> new Position(-1, 0);
        };
    }

    public Direction opposite() {
        return switch (this) {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case EAST -> Direction.WEST;
            case WEST -> Direction.EAST;
        };
    }

    public static Direction getDirections(char character) {
        return switch (character) {
            case 'N' -> Direction.NORTH;
            case 'S' -> Direction.SOUTH;
            case 'E' -> Direction.EAST;
            case 'W' -> Direction.WEST;
            default -> null;
        };
    }
}