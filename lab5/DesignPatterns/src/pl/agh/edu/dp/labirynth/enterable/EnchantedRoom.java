package pl.agh.edu.dp.labirynth.enterable;

import pl.agh.edu.dp.labirynth.Position;

public class EnchantedRoom extends Room {
    public EnchantedRoom(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "enchanted room";
    }
}
