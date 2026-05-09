package org.alerick;

import java.util.List;

public class Teacher extends User {

    public Teacher(String name) {
        super(name);
        this.borrowingLimit = Constants.TEACHERLIMIT;
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
