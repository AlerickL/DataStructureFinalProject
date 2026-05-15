package org.alerick;

import java.util.List;

public class Student extends User {

    public Student(String name) {
        super(name);
        this.borrowingLimit = Constants.STUDENTLIMIT;
    }

    @Override
    public String toString() {
        return "s" + ',' + super.toString() + "\n";
    }
}
