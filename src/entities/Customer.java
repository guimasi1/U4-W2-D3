package entities;

import java.util.Random;

public class Customer {
    long id;
    String name;
    int tier;

    public Customer(String name, int tier) {
        Random rnd = new Random();
        this.id = rnd.nextLong(0, 10000000);
        this.name = name;
        this.tier = tier;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
