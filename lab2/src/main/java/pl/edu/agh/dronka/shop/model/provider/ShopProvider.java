package pl.edu.agh.dronka.shop.model.provider;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Index;
import pl.edu.agh.dronka.shop.model.items.Item;
import pl.edu.agh.dronka.shop.model.Shop;
import pl.edu.agh.dronka.shop.model.User;

public class ShopProvider {

	public static Shop getExampleShop() {
		Shop shop = new Shop();

		shop.addUser(getExampleUser());

		Index itemsIndex = new Index();

		for (Item item : getExampleItems()) {
			itemsIndex.addItem(item);
		}

		registerExampleCategories(itemsIndex);
		shop.setItemsIndex(itemsIndex);

		return shop;
	}

	public static User getExampleUser() {
		return new User("Jan", "Rejnor");
	}

	public static List<Item> getExampleItems() {
		List<Item> items = new ArrayList<>();

		//new ItemCreator().createItems(filename, Category.BOOKS);
		CSVReader booksReader = new CSVReader("src/main/resources/books.csv");
		items.addAll(readItems(booksReader, Category.BOOKS));
		
		CSVReader electronicsReader = new CSVReader("src/main/resources/electronics.csv");
		items.addAll(readItems(electronicsReader, Category.ELECTRONICS));
		
		CSVReader foodReader = new CSVReader("src/main/resources/food.csv");
		items.addAll(readItems(foodReader, Category.FOOD));
		
		CSVReader musicReader = new CSVReader("src/main/resources/music.csv");
		items.addAll(readItems(musicReader, Category.MUSIC));
		
		CSVReader sportReader = new CSVReader("src/main/resources/sport.csv");
		items.addAll(readItems(sportReader, Category.SPORT));

		return items;
	}

	public static void registerExampleCategories(Index index) {
		for (Category category : Category.values()) {
			index.registerCategory(category);
		}
	}

	private static List<Item> readItems(CSVReader reader, Category category) {
		List<Item> items = new ArrayList<>();

		try {
			reader.parse();
			List<String[]> data = reader.getData();

			for (String[] dataLine : data) {

				// read properties common to all item categories
				String name = reader.getValue(dataLine,"Nazwa");
				int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
				int quantity = Integer.parseInt(reader.getValue(dataLine,
						"Ilość"));
				boolean isPolish = Boolean.parseBoolean(reader.getValue(
						dataLine, "Tanie bo polskie"));
				boolean isSecondhand = Boolean.parseBoolean(reader.getValue(
						dataLine, "Używany"));

				// create needed item provider
				ItemProvider itemProvider = ItemProviderFactory.getItemProvider(category);

				// order the item provider to finish reading from CSV and to crate desired item
				Item item = itemProvider.createItem(name, price, quantity, category, dataLine, reader);


				item.setPolish(isPolish);
				item.setSecondhand(isSecondhand);

				items.add(item);

			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return items;
	}

}
