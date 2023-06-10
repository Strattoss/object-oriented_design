package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.FoodItem;
import pl.edu.agh.dronka.shop.model.items.Item;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodProvider implements ItemProvider{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

    @Override
    public Item createItem(String name, int price, int quantity, Category category, String[] dataLine, CSVReader csvReader) throws ParseException {
        Date expirationDate = formatter.parse(csvReader.getValue(dataLine, "Data przydatności"));
        return new FoodItem(name, category, price, quantity, expirationDate);
    }
}
