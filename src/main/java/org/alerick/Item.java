package org.alerick;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Item {
    protected String id;
    protected String title;
    private static int nextId = 1;
    protected Status status;
    protected String author;
    private static List<Item> list = new ArrayList<>();

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

    @Override
    public String toString() {
        return title + ',' + status + ',' + author;
    }
}
