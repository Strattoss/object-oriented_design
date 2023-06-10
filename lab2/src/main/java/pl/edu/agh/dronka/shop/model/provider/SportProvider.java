package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.Item;
import pl.edu.agh.dronka.shop.model.items.SportItem;

import java.text.ParseException;

public class SportProvider implements ItemProvider{
    @Override
    public Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) {
        return new SportItem(name, category, price, quantity);
    }
}
