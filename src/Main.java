import entities.Category;
import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Supplier<Integer> randomNumbers = () -> {
            Random rnd = new Random();
            return rnd.nextInt(1, 200);
        };

        Predicate<Product> isPriceOver100 = product -> product.getPrice() > 100;
        Predicate<Product> isBooks = product -> product.getCategory().equals("Books");
        Predicate<Product> isBabyProduct = product -> product.getCategory().equals("Baby");
        Predicate<Product> isBoysProduct = product -> product.getCategory().equals("Boys");
        Predicate<Order> hasBabyProduct = order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby"));


        Supplier<Product> booksSupplier = () -> new Product("Libro", Category.Books.toString(), randomNumbers.get());
        Supplier<Product> babyProdsSupplier = () -> new Product("GiocoX", Category.Baby.toString(), randomNumbers.get());
        Supplier<Product> boysProdsSupplier = () -> new Product("Camicia", Category.Boys.toString(), randomNumbers.get());

        // CLIENTI
        Supplier<Customer> customerSupplier = () -> new Customer("Pippo", 1);
        Supplier<Customer> customer2Supplier = () -> new Customer("Franco", 2);

        // CLIENTI TIER 1
        Customer customer1 = customerSupplier.get();
        Customer customer4 = new Customer("Leopoldo", 1);

        // CLIENTI TIER 2
        Customer customer2 = customer2Supplier.get();
        Customer customer3 = new Customer("Giacomo", 2);

        // TUTTI I CLIENTI
        List<Customer> allCustomers = new ArrayList<>();
        allCustomers.add(customer1);
        allCustomers.add(customer2);
        allCustomers.add(customer3);
        allCustomers.add((customer4));

        // PRODOTTI

        // 1. LIBRI
        List<Product> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            books.add(booksSupplier.get());
        }

        // 2. PER BAMBINI

        List<Product> babyProducts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            babyProducts.add(babyProdsSupplier.get());
        }

        // 3. PER RAGAZZI

        List<Product> boysProducs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boysProducs.add(boysProdsSupplier.get());
        }

        // TUTTI I PRODOTTI

        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(boysProducs);
        allProducts.addAll(babyProducts);
        allProducts.addAll(books);

        // Per stampare i prodotti che hanno un prezzo maggiore di 100.

        // allProducts.forEach(product -> {
        //     if(isPriceOver100.test(product)) {
        //     System.out.println(product.getPrice());
        //     }
        // });

        // ORDINI
        LocalDate today = LocalDate.now();
        LocalDate secondFeb = LocalDate.parse("2021-02-02");
        LocalDate thirtyMarch = LocalDate.parse("2021-03-30");
        LocalDate twentyMarch = LocalDate.parse("2021-03-20");
        LocalDate orderDate1 = LocalDate.parse("2023-10-10");

        Supplier<Order> deliveredOrdersBabySupplier = () -> new Order("DELIVERED", secondFeb, thirtyMarch, babyProducts, customer2);
        Order order1 = deliveredOrdersBabySupplier.get();
        Order order2 = new Order("DELIVERED", twentyMarch, thirtyMarch, boysProducs, customer3);
        Order order3 = new Order("DELIVERED", orderDate1, today, books, customer1);
        Order order4 = new Order("DELIVERED", orderDate1, today, babyProducts, customer4);
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        allOrders.add(order2);
        allOrders.add(order3);
        allOrders.add(order4);

        allOrders.forEach(order -> System.out.println(order.getProducts()));

        // ESERCIZIO 1 - ottenere una lista di libri con prezzo sopra 100.

        Stream<Product> expensiveBooks = allProducts.stream().filter(isBooks.and(isPriceOver100));
        expensiveBooks.forEach(product -> System.out.println("Category: " + product.getCategory() + " Price: " + product.getPrice()));

        // ESERCIZIO 2 - ottenere una lista di ordini con prodotti che appartengono alla categoria Baby.

        Stream<Order> ordersWithBabyProducts = allOrders.stream().filter(hasBabyProduct);
        System.out.println("Ordini con prodotti baby al loro interno.");
        ordersWithBabyProducts.forEach(order -> System.out.println(order.getProducts()));

        // ESERCIZIO 3 - ottenere una lista di prodotti che appartengono alla categoria Boys e applicare lo sconto del 10%.

        List<Product> boysProducts = allProducts.stream().filter(isBoysProduct).toList();
        List<Double> boysProductsWithDiscount = boysProducts.stream().map(product -> product.getPrice() * 0.9).toList();

        System.out.println("Prodotti boys");
        boysProducts.forEach(System.out::println);
        System.out.println("Prezzi dei prodotti boys scontati.");
        boysProductsWithDiscount.forEach(price -> System.out.println(price));

        // ESERCIZIO 4 - ottenere una lista di prodotti ordinati da clienti di tier 2 tra il primo febbraio 2021 e il primo aprile 2021.
        LocalDate feb1 = LocalDate.parse("2021-02-01");
        LocalDate april1 = LocalDate.parse("2021-04-01");
        // predicato che verifica che sia dopo il primo febbraio.
        Predicate<Order> isAfterFeb1 = order -> order.getOrderDate().isAfter(feb1);
        // predicato che verifica che sia prima del primo aprile.
        Predicate<Order> isBeforeApril1 = order -> order.getOrderDate().isBefore(april1);
        Predicate<Customer> isCustomerTier2 = customer -> customer.getTier() == 2;
        // ordini filtrati in base alla data
        List<Order> filteredOrdersBasedOnDate = allOrders.stream().filter(isAfterFeb1.and(isBeforeApril1)).toList();
        // ordini filtrati in base al tier del cliente
        List<Order> filterOrdersBasedOnTier = filteredOrdersBasedOnDate.stream().filter(order -> isCustomerTier2.test(order.getCustomer())).toList();
        // prodotti filtrati in base ad entrambe le condizioni (data e livello cliente)
        List<Product> filteredProductsBasedOnDateAndTier = filterOrdersBasedOnTier.stream().flatMap(order -> order.getProducts().stream()).toList();

        System.out.println("Prodotti filtrati in base al tier del cliente e la data.");
        filteredProductsBasedOnDateAndTier.forEach(System.out::println);
    }
}