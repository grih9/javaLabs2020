package ru.spbstu.lab4.service;

import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.*;
import java.util.function.Consumer;

public class DBService {

    private ProductDB productDB;
    private final Scanner in;
    private final PrintStream out;
    private Connection connection;
    private final Map<String, Consumer<Scanner>> functions = Map.of(
            "/add", this::add,
            "/delete", this::delete,
            "/show_all", this::showAll,
            "/price", this::price,
            "/change_price", this::changePrice,
            "/filter_by_price", this::filterByPrice
    );

    public DBService() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public DBService(InputStream in, PrintStream out, Connection connection) {
        this.in = new Scanner(in);
        this.out = out;
        this.connection = connection;
        productDB = new ProductDB(this.connection);
        out.println("Пожалуйста, веведите число автоматически сгенерированных товаров: ");
        final int size = this.in.nextInt();
        out.println("Генерация...");
        //productDB.clear();
        for (int i = 0; i < size; i++) {
            productDB.add(new Product(i, UUID.randomUUID().toString(), "товар" + i, i * 10));
        }
        out.println("Таблица успешно сгенерирована.");
    }

    public void start() {
        while(in.hasNextLine()) {
            try {
                final String command = in.next();
                if (command.equals("/exit")) {
                    break;
                }
                execute(command + in.nextLine());
            }  catch (RuntimeException e) {
                out.println(e.getMessage());
            }
        }
    }

    public void execute(String commandLine) {
        final Scanner line = new Scanner(commandLine);

        if (line.hasNext()) {
            functions.getOrDefault(line.next(), (v) -> out.println("Такой команды нет.")).accept(line);
        }
    }

    private void add(Scanner args) {
        try {
            final String title = args.next();
            final int cost = args.nextInt();
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            productDB.add(new Product(0, UUID.randomUUID().toString(), title, cost));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void delete(Scanner args) {
        try {
            final String title = args.next();
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            productDB.removeByTitle(title);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void showAll(Scanner args) {
        try {
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            List<Product> products = productDB.getList();
            while (!products.isEmpty()) {
                Product product = products.remove(0);
                out.println(product.getId() + " : " + product.getProdid() +
                        " : " + product.getTitle() + " : " + product.getCost());
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void price(Scanner args) {
        try {
            final String title = args.next();
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            productDB.printCost(title);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void changePrice(Scanner args) {
        try {
            final String title = args.next();
            final int cost = args.nextInt();
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            productDB.changeCost(title, cost);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void filterByPrice(Scanner args) {
        try {
            final int from = args.nextInt();
            final int to = args.nextInt();
            if (args.hasNext()) {
                throw new RuntimeException("Неверное число аргументов.");
            }
            productDB.printFilteredTable(from, to);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }
}
