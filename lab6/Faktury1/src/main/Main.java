package main;
import java.util.ArrayList;
import java.util.List;


    // Interfejs dla zarówno kategorii, jak i podkategorii
    interface Component {
        void print();
    }

    // Klasa reprezentująca kategorię
    class Category implements Component {
        private String name;
        private List<Component> components;

        public Category(String name) {
            this.name = name;
            this.components = new ArrayList<>();
        }

        public void addComponent(Component component) {
            components.add(component);
        }

        @Override
        public void print() {
            System.out.println("Category: " + name);
            for (Component component : components) {
                component.print();
            }
        }
    }

    // Klasa reprezentująca podkategorię
    class Subcategory implements Component {
        private String name;
        private List<Component> components;

        public Subcategory(String name) {
            this.name = name;
            this.components = new ArrayList<>();
        }

        public void addComponent(Component component) {
            components.add(component);
        }

        @Override
        public void print() {
            System.out.println("Subcategory: " + name);
            for (Component component : components) {
                component.print();
            }
        }
    }

    // Klasa reprezentująca towar
    class Product implements Component {
        private String name;

        public Product(String name) {
            this.name = name;
        }

        @Override
        public void print() {
            System.out.println("Product: " + name);
        }
    }

    public class Main {
        public static void main(String[] args) {
            // Tworzenie drzewa kategorii towarów
            Category rootCategory = new Category("Root Category");

            Category category1 = new Category("Category 1");
            Category category2 = new Category("Category 2");

            Subcategory subcategory1 = new Subcategory("Subcategory 1");
            Subcategory subcategory2 = new Subcategory("Subcategory 2");

            Product product1 = new Product("Product 1");
            Product product2 = new Product("Product 2");
            Product product3 = new Product("Product 3");

            // Dodawanie towarów do odpowiednich kategorii i podkategorii
            rootCategory.addComponent(category1);
            rootCategory.addComponent(category2);

            category1.addComponent(subcategory1);
            category2.addComponent(subcategory2);

            subcategory1.addComponent(product1);
            subcategory1.addComponent(product2);
            subcategory2.addComponent(product3);

            // Wypisanie drzewa wraz z zawartościami kategorii i podkategorii
            rootCategory.print();
        }
    }

