package org.alerick;

import java.util.List;

public class Student extends User {

    public Student(String id, String name) {
        super(name);
        this.borrowingLimit = Constants.STUDENTLIMIT;
    }
}
