package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.ElectronicItem;
import pl.edu.agh.dronka.shop.model.items.Item;

public class ElectronicProvider implements ItemProvider{
    @Override
    public Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) {
        boolean isMobile = Boolean.parseBoolean(csvReader.getValue(dataLine, "Mobilny"));
        boolean isUnderWarranty = Boolean.parseBoolean(csvReader.getValue(dataLine, "Gwarancja"));

        return new ElectronicItem(name, category, price, quantity, isMobile, isUnderWarranty);
    }
}
