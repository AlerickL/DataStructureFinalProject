package org.alerick;

public class DVD extends Item {
    private double duration;

    public DVD(String title, Status status, String author, double duration) {
        super(title, status, author);
        this.duration = duration;
    }
}
