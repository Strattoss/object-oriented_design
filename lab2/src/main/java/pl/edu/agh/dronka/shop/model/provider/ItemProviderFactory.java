package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;

public class ItemProviderFactory {
    public static ItemProvider getItemProvider(Category category) {
        switch (category) {
            case FOOD -> {
                return new FoodProvider();
            }
            case BOOKS -> {
                return new BookProvider();
            }
            case MUSIC -> {
                return new MusicProvider();
            }
            case SPORT -> {
                return new SportProvider();
            }
            case ELECTRONICS -> {
                return new ElectronicProvider();
            }
        }

        return null;
    }
}
