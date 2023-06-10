package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.items.*;

public class ItemFilter {

	private final Item itemSpec;

	public ItemFilter(Category category) {
		this.itemSpec = switch (category) {
			case BOOKS -> new BookItem();
			case ELECTRONICS -> new ElectronicItem();
			case FOOD -> new FoodItem();
			case MUSIC -> new MusicItem();
			case SPORT -> new SportItem();
		};
	}

	public Item getItemSpec() {
		return itemSpec;
	}

	public boolean appliesTo(Item item) {
		if (!item.getCategory().equals(itemSpec.getCategory())) {
			return false;
		}

		if (itemSpec.getName() != null
				&& !itemSpec.getName().equals(item.getName())) {
			return false;
		}
		if (itemSpec.getCategory() != null
				&& !itemSpec.getCategory().equals(item.getCategory())) {
			return false;
		}

		// applies filter only if the flag (secondHand) is true)
		if (itemSpec.isSecondhand() && !item.isSecondhand()) {
			return false;
		}

		// applies filter only if the flag (polish) is true)
		if (itemSpec.isPolish() && !item.isPolish()) {
			return false;
		}

		if (!item.satisfiesFilterExtraProperties(itemSpec)) {
			return false;
		}

		return true;
	}

}