package entities;

import java.util.Random;

public class Product {
    long id;
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        Random rnd = new Random();
        this.id = rnd.nextLong(0, 1000000);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
