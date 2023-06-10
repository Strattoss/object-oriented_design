package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;

public class ElectronicItem extends Item{
    private boolean isMobile;
    private boolean isUnderWarranty;

    public ElectronicItem(String name, Category category, int price, int quantity, boolean isMobile, boolean isUnderWarranty) {
        super(name, category, price, quantity);
        this.isMobile = isMobile;
        this.isUnderWarranty = isUnderWarranty;
    }

    public ElectronicItem() {
       super(Category.ELECTRONICS);
    }

    @Override
    public Map<String, Object> getExtraProperties() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Mobilny", isMobile);
        result.put("Na gwarancji", isUnderWarranty);

        return result;
    }

    @Override
    public boolean satisfiesFilterExtraProperties(Item item) {
        if (!(item instanceof ElectronicItem referenceItem)) {
            return false;
        }

        if (referenceItem.isMobile && !this.isMobile) {
            return false;
        }

        if (referenceItem.isUnderWarranty && !this.isUnderWarranty) {
            return false;
        }

        return true;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isUnderWarranty() {
        return isUnderWarranty;
    }

    public void setUnderWarranty(boolean underWarranty) {
        isUnderWarranty = underWarranty;
    }
}
