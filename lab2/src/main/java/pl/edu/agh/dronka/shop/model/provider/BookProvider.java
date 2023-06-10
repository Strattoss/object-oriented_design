package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.BookItem;
import pl.edu.agh.dronka.shop.model.items.Item;

public class BookProvider implements ItemProvider{
    @Override
    public Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) {
        int numOfPages = Integer.parseInt(csvReader.getValue(dataLine, "Liczba stron"));
        boolean isHardcover = Boolean.parseBoolean(csvReader.getValue(dataLine, "Twarda oprawa"));

        return new BookItem(name, category, price, quantity, numOfPages, isHardcover);
    }
}
