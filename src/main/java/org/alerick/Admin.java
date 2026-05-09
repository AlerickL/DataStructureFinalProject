package org.alerick;

import java.util.List;

public class Admin extends User {

    public Admin(String name) {
        super(name);
        this.borrowingLimit = Integer.MAX_VALUE;
    }
}
