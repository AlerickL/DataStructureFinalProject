package org.alerick;

import java.util.List;

public class Item implements Reportable{
    protected String id;
    protected String title;
    private static int nextId = 1;
    protected Status status;
    protected String author;

    public Item(String title, Status status, String author) {
        this.id = String.format("%05d", nextId++);
        this.title = title;
        this.status = status;
        this.author = author;
    }

    @Override
    public List<Item> report() {
        return List.of();
    }
}
