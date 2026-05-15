package org.alerick;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
     * initializes users
     */
    public static void initUsers() {
        File file = new File(Constants.USERS_CSV_PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                switch (elements[0]) {
                    case "s" -> {
                        Student student = new Student(elements[1]);
                        int size = elements.length - 2;
                        for (int i = size; i > 0; i--) {
                            List<Item> match = Item.list.stream()
                                    .filter(item -> item.id.equals(elements[1 + size]))
                                    .toList();
                            student.borrowItem(match.getFirst());
                        }
                    }
                    case "t" -> {Teacher teacher = new Teacher(elements[1]);
                        int size = elements.length - 2;
                        for (int i = size; i > 0; i--) {
                            List<Item> match = Item.list.stream()
                                    .filter(item -> item.id.equals(elements[1 + size]))
                                    .toList();
                            teacher.borrowItem(match.getFirst());
                        }
                    }
                    case "a" -> {Admin admin = new Admin(elements[1]);
                        int size = elements.length - 2;
                        for (int i = size; i > 0; i--) {
                            List<Item> match = Item.list.stream()
                                    .filter(item -> item.id.equals(elements[1 + size]))
                                    .toList();
                            admin.borrowItem(match.getFirst());
                        }
                    }
                }
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

    /**
     * searches a user's items using recursion technology
     * @param searchType the search type
     * @param string the string to search
     * @param items the items to be searched
     * @return the items that match the requisites.
     */
    public String searchRecursion(SearchType searchType, String string, List<Item> items) {
        if (items.isEmpty()) {
            return "";
        }
        String match = "";
        switch (searchType) {
            case AUTHOR -> {
                if (items.getFirst().author.contains(string)) {
                    match = items.getFirst().toString();
                }
            }
            case TITLE -> {
                if (items.getFirst().title.contains(string)) {
                    match = items.getFirst().toString();
                }
            }
        }

        items.removeFirst();
        return match + "\n" + searchRecursion(searchType, string, items);
    }

    @Override
    public String toString() {
        StringBuilder ids = new StringBuilder();
        for (Item item : borrowedItems) {
            ids.append(",").append(item.id);
        }
        return name + ids;
    }

    public static class UserComparator implements Comparator<User> {
        private SortType sortType;

        public UserComparator(SortType sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(User o1, User o2) {
            if (sortType.equals(SortType.BORROWING_LIMIT) && o1.borrowingLimit != o2.borrowingLimit) {
                return -1 * (o1.borrowingLimit - o2.borrowingLimit);
            } else if (sortType.equals(SortType.NAME) && !o1.name.equals(o2.name)) {
                return o1.name.compareTo(o2.name);
            } else {
                return o1.id.compareTo(o2.id);
            }
        }
    }
    public enum SortType {
        NAME,
        BORROWING_LIMIT,
    }
}
