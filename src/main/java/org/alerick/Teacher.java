package org.alerick;

import java.util.List;

public class Teacher extends User {

    public Teacher(String name) {
        super(name);
        this.borrowingLimit = Constants.TEACHERLIMIT;
    }

    @Override
    public String toString() {
        return "t" + ',' + super.toString() + "\n";
    }

}
