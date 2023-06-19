package pl.agh.edu.dp.labirynth.enterable;

import pl.agh.edu.dp.labirynth.Position;

public class Door implements WallLike, Enterable {
    private final Room room1;
    private final Room room2;
    private boolean locked = false;

    public Door(Room r1, Room r2){
        this.room1 = r1;
        this.room2 = r2;
    }

    @Override
    public boolean canEnter() {
        return !this.locked;
    }

    @Override
    public Room enter(Room currentRoom){
        return canEnter() ? this.takeTheOtherRoom(currentRoom).enter(currentRoom) : currentRoom;
    }

    private Room takeTheOtherRoom(Room room) {
        if (room != room1 && room != room2) {
            throw new IllegalArgumentException("Room " + room.toString() + " isn't connected to this door");
        }
        return (room == room1) ? room2 : room1;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public Room getRoom1() {
        return room1;
    }
    public Room getRoom2() {
        return room2;
    }

    @Override
    public String toString() {
        return "door";
    }
}
