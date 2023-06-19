package pl.agh.edu.dp.labirynth.enterable;

public class EnchantedDoor extends Door{
    public EnchantedDoor(Room r1, Room r2) {
        super(r1, r2);
    }

    @Override
    public String toString() {
        return "enchanted door";
    }
}
