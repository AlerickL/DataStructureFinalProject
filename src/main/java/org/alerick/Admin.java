package org.alerick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements Reportable {

    public Admin(String name) {
        super(name);
        this.borrowingLimit = Integer.MAX_VALUE;
    }

    public String report() {
        File file = new File(Constants.ITEMS_CSV_PATH);
        StringBuilder items = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] elements = line.split(",");
                items.append(elements[2])
                        .append(": ")
                        .append(line)
                        .append("\n");
            }
            return items.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Backs up current Items and Users into two CSV files
     */
    public static void backup() {
        User.backUpUsers();
        Item.backUpItems();
    }

    @Override
    public String toString() {
        return "a" + ',' + super.toString() + "\n";
    }
}
