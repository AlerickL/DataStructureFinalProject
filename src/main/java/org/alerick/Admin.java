package org.alerick;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String name) {
        super(name);
        this.borrowingLimit = Integer.MAX_VALUE;
    }

    public static List<Item> report(SearchType searchType, String string) {
        return List.of();
    }

    /**
     * Backs up current Items and Users into two CSV files
     */
    public static void backup() {
    }

    @Override
    public String toString() {
        return "a" + ',' + super.toString() + "\n";
    }
}
