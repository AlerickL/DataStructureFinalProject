package org.alerick;

import java.util.List;

public class Admin extends User {

    public Admin(String name) {
        super(name);
        this.borrowingLimit = Integer.MAX_VALUE;
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
