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

    protected abstract boolean borrowItem(Item item);
    protected abstract boolean returnItem(Item item);
    protected abstract List<Item> search(SearchType searchType, String string);
}
