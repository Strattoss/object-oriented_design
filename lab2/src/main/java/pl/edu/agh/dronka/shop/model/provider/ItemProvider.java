package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.Item;

import java.text.ParseException;
import java.util.List;

public interface ItemProvider {
    Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) throws ParseException;
}
