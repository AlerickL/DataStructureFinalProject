package org.alerick;

import java.util.List;

public class Student extends User {

    public Student(String id, String name) {
        super(name);
        this.borrowingLimit = Constants.STUDENTLIMIT;
    }

    @Override
    protected boolean borrowItem(Item item) {
        return false;
    }

    @Override
    protected boolean returnItem(Item item) {
        return false;
    }

    @Override
    protected List<Item> search(SearchType searchType, String string) {
        return List.of();
    }
}
