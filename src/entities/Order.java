package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    long id;
    String status;
    LocalDate orderDate;
    LocalDate deliveryDate;
    List<Product> products;
    Customer customer;

    public Order(String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> products, Customer customer) {
        Random rnd = new Random();
        this.id = rnd.nextLong(0, 10000000);
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.products = products;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}
