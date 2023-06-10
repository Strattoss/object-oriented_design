package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;

public class BookItem extends Item{
    private int numOfPages;
    private boolean isHardcover;

    public BookItem(String name, Category category, int price, int quantity, int numOfPages, boolean isHardcover) {
        super(name, category, price, quantity);
        this.numOfPages = numOfPages;
        this.isHardcover = isHardcover;
    }

    public BookItem() {
        super(Category.BOOKS);
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Liczba stron", numOfPages);
        result.put("Twarda okładka", isHardcover);

        return result;
    }

    @Override
    public boolean satisfiesFilterExtraProperties(Item item) {
        if (!(item instanceof BookItem referenceItem)) {
            return false;
        }
        return !(referenceItem.isHardcover && !this.isHardcover);
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public boolean isHardcover() {
        return isHardcover;
    }

    public void setHardcover(boolean hardcover) {
        isHardcover = hardcover;
    }
}
