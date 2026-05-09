package org.alerick;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String id;
    protected String name;
    protected List<Item> borrowedItems;
    protected int borrowingLimit;
    private static int nextId = 1;

    public User(String name) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Borrows a specific item, changing its status.
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

    protected List<Item> search(SearchType searchType, String string) {
        return List.of();
    }
}
