package ru.spbstu.lab4.service;

import ru.spbstu.lab4.model.Product;
import ru.spbstu.lab4.repository.ProductDB;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Consumer;

public class DBService {

    private ProductDB productDB;
    private final Scanner in;
    private final PrintStream out;
    private Connection connection;
    private final Map<String, Consumer<Scanner>> functions = Map.of(
            "/add", DBService.this::add,
            "/delete", DBService.this::delete,
            "/show_all", DBService.this::showAll,
            "/price", DBService.this::price,
            "/change_price", DBService.this::changePrice,
            "/filter_by_price", DBService.this::filterByPrice
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
            functions.getOrDefault(line.next(), (c) -> out.println("Такой команды нет.")).accept(line);
        }
    }

    private void add(Scanner args) {
        try {
            final String title = args.next();
            final int cost = args.nextInt();
            productDB.add(new Product(0, UUID.randomUUID().toString(), title, cost));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void delete(Scanner args) {
        try {
            final String title = args.next();
            productDB.removeByTitle(title);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void showAll(Scanner args) {
        try {
            Connection connection = null;
            productDB.printTable();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void price(Scanner args) {
        try {
            final String title = args.next();
            productDB.printCost(title);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void changePrice(Scanner args) {
        try {
            final String title = args.next();
            final int cost = args.nextInt();
            productDB.changeCost(title, cost);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }

    private void filterByPrice(Scanner args) {
        try {
            final int from = args.nextInt();
            final int to = args.nextInt();
            productDB.printFilteredTable(from, to);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Неверная команда.");
        }
    }
}
