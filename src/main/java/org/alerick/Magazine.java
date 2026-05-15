package org.alerick;

public class Magazine extends Item {
    private int issueNumber;

    public Magazine(String title, Status status, String author, int issueNumber) {
        super(title, status, author);
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return "m" + ',' + super.toString() + ',' + issueNumber + '\n';
    }
}
