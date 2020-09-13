package app;

import exception.NoSuchOptionException;

public enum Option {

    EXIT(0, " - exit"),
    ADD_BOOK(1, " - add new book"),
    ADD_MAGAZINE(2, " - add new magazine"),
    PRINT_BOOKS(3, " - print info about all books"),
    PRINT_MAGAZINES(4, " - print info about all magazines");

    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.value + " " + this.description;
    }

    static Option createFromInt(int option) throws NoSuchOptionException {
        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("No such option: " + option + ", try again!");
        }
    }
}
