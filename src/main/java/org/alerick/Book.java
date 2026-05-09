package org.alerick;

public class Book extends Item {
    private String ISBN;
    private String genre;

    public Book(String title, Status status, String author, String ISBN, String genre) {
        super(title, status, author);
        this.ISBN = verifyISBN(ISBN);
        this.genre = genre;
    }

    /**
     * checks the validity of the ISBN
     * @param str the ISBN to be checked
     * @return INVALID if the ISBN does not follow adequate formatting, or the initial ISBN if it's correct
     */
    public static String verifyISBN(String str) {
        if (str.length() != 12 ) {
            return "INVALID";
        }
        for (Character c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return "INVALID";
            }
        }
        return str;
    }
}
