package org.alerick;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Item {
    protected String id;
    protected String title;
    private static int nextId = 1;
    protected Status status;
    protected String author;
    public static List<Item> list = new ArrayList<>();

    public Item(String title, Status status, String author) {
        this.id = String.format("%05d", nextId++);
        this.title = title;
        this.status = status;
        this.author = author;
        list.add(this);
    }

    /**
     * backs up all existing items in the csv file.
     */
    public static void backUpItems() {
        File file = new File(Constants.ITEMS_CSV_PATH);
        try (FileWriter fw = new FileWriter(file)) {
            for (Item item : list) {
                fw.write(item.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes items
     */
    public static void initItems() {
        File file = new File(Constants.ITEMS_CSV_PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                switch (elements[0]) {
                    case "b" -> {
                        Status status = null;
                        switch (elements[2]) {
                            case "LOST" -> {status = Status.LOST;}
                            case "IN_STORE" -> {status = Status.IN_STORE;}
                            case "BORROWED" -> {status = Status.BORROWED;}
                        }
                        Book book = new Book(elements[1], status, elements[3], elements[4], elements[5]);
                    }
                    case "d" -> {
                        Status status = null;
                        switch (elements[2]) {
                            case "LOST" -> {status = Status.LOST;}
                            case "IN_STORE" -> {status = Status.IN_STORE;}
                            case "BORROWED" -> {status = Status.BORROWED;}
                        }
                        DVD dvd = new DVD(elements[1], status, elements[3], Double.parseDouble(elements[4]));
                    }
                    case "m" -> {
                        Status status = null;
                        switch (elements[2]) {
                            case "LOST" -> {status = Status.LOST;}
                            case "IN_STORE" -> {status = Status.IN_STORE;}
                            case "BORROWED" -> {status = Status.BORROWED;}
                        }
                        Magazine magazine = new Magazine(elements[1], status, elements[3], Integer.parseInt(elements[4]));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return title + ',' + status + ',' + author;
    }
}
