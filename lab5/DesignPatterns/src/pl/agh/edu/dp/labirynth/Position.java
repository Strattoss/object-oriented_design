package pl.agh.edu.dp.labirynth;

import java.util.*;

import static java.lang.Math.abs;

public class Position {
    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other position
     * @return true if positions are neighbours on a 2d grid (directly, not diagonally), else false
     */
    public boolean areNeighbours(Position other) {
        return abs(this.x - other.x) == 1 && abs(this.y - other.y) == 0 ||
                abs(this.x - other.x) == 0 && abs(this.y - other.y) == 1;
    }

    public Collection<Position> getNeighbouringPositions() {
        return new ArrayList<>(Arrays.asList(
                new Position(this.x - 1, this.y),
                new Position(this.x + 1, this.y),
                new Position(this.x, this.y - 1),
                new Position(this.x, this.y + 1)
        ));
    }

    public Position add(Position other) {
        return new Position(this.x + other.x, this.y + other.y);
    }

    public Position opposite() {
        return new Position(-this.x, -this.y);
    }

    public Position subtract(Position other) {
        return this.add(other.opposite());
    }

    /**
     * Converts Position to Direction type. WARNING - position has to be a unit vector: (0, 1), (0, -1), (1, 0) or (-1, 0)
     * @throws IllegalArgumentException if position is not a unit vector
     * @return direction; if position is not a unit vector - returns null
     */
    private Direction toDirection() {
        if (this.equals(new Position(1, 0))) return Direction.WEST;
        if (this.equals(new Position(-1, 0))) return Direction.EAST;
        if (this.equals(new Position(0, 1))) return Direction.NORTH;
        if (this.equals(new Position(0, -1))) return Direction.SOUTH;
        throw new IllegalArgumentException("Position " + this.toString() + " cannot be converted to direction, because it's not a unit vector");
    }

    /**
     * Get a direction as if you were standing on this position and facing otherPosition. Positions have to be neighbours
     * @param otherPosition the position you are facing
     * @throws IllegalArgumentException if positions are not neighbours
     * @return direction
     */
    public Direction getDirectionFacingOtherPosition(Position otherPosition) {
        return otherPosition.subtract(this).toDirection();
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
