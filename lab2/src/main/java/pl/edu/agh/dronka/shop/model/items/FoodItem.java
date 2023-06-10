package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FoodItem extends Item{
    private Date expirationDate;

    public FoodItem(String name, Category category, int price, int quantity, Date expirationDate) {
        super(name, category, price, quantity);
        this.expirationDate = expirationDate;
    }

    public FoodItem() {
        super(Category.FOOD);
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Data przydatności", expirationDate);

        return result;
    }

    @Override
    public boolean satisfiesFilterExtraProperties(Item item) {
        return item instanceof FoodItem;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
