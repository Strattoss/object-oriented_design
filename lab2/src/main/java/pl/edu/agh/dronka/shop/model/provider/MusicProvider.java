package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.Item;
import pl.edu.agh.dronka.shop.model.items.MusicGenre;
import pl.edu.agh.dronka.shop.model.items.MusicItem;

import java.text.ParseException;

public class MusicProvider implements ItemProvider{
    @Override
    public Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) {
        MusicGenre genre = MusicGenre.valueOf(csvReader.getValue(dataLine, "Gatunek").toUpperCase());
        boolean hasAttachedVideo = Boolean.parseBoolean(csvReader.getValue(dataLine, "Dołączone wideo"));
        return new MusicItem(name, category, price, quantity, genre, hasAttachedVideo);
    }
}
