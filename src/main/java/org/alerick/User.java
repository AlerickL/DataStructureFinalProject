package org.alerick;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
    protected String id;
    protected String name;
    protected List<Item> borrowedItems;
    protected int borrowingLimit;
    private static int nextId = 1;
    private static List<User> list = new ArrayList<>();

    public User(String name) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        list.add(this);

        File file = new File(Constants.USERS_CSV_PATH);
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(id + "," + name + "," + borrowedItems + ",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Borrows a specific item, changing its status.
     *
     * @param item the item to borrow
     * @return if the operation succeeded
     */
    protected boolean borrowItem(Item item) {
        try {
            if (item.status == Status.IN_STORE && borrowedItems.size() < borrowingLimit) {
                this.borrowedItems.add(item);
                item.status = Status.BORROWED;
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException exception) {
            return false;
        }
    }

    /**
     * Returns an item
     *
     * @param item the item to be removed
     * @return is the operation succeeded
     */
    protected boolean returnItem(Item item) {
        try {
            if (borrowedItems.contains(item)) {
                borrowedItems.remove(item);
                item.status = Status.IN_STORE;
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException exception) {
            return false;
        }
    }

    /**
     * backs up all existing items in the csv file.
     */
    public static void backUpUsers() {
        File file = new File(Constants.USERS_CSV_PATH);
        try (FileWriter fw = new FileWriter(file)) {
            for (User user : list) {
                fw.write(user.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * searches a user's items using stream technology
     * @param searchType the search type
     * @param string the string to search
     * @return the items that match the requisites.
     */
    public String searchStream(SearchType searchType, String string) {
        switch (searchType) {
            case AUTHOR -> {
                return borrowedItems.stream()
                        .filter(item -> item.author.contains(string))
                        .toString();
            }
            case TITLE -> {
                return borrowedItems.stream()
                        .filter(item -> item.title.contains(string))
                        .toString();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder ids = new StringBuilder();
        for (Item item : borrowedItems) {
            ids.append(",").append(item.id);
        }
        return name + ids;
    }
}
