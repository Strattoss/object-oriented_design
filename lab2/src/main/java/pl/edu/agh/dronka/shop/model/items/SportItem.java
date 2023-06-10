package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;

public class SportItem extends Item {
    public SportItem(String name, Category category, int price, int quantity) {
        super(name, category, price, quantity);
    }

    public SportItem() {
        super(Category.SPORT);
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        return new HashMap<>();
    }

    @Override
    public boolean satisfiesFilterExtraProperties(Item item) {
        return item instanceof ElectronicItem;
    }
}
