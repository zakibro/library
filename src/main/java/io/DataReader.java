package io;

import model.Book;
import model.Magazine;

import java.util.Scanner;

public class DataReader {

    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook() {
        printer.printLine("Title:");
        String title = scanner.nextLine();
        printer.printLine("Author:");
        String author = scanner.nextLine();
        printer.printLine("Publisher:");
        String publisher = scanner.nextLine();
        printer.printLine("ISBN:");
        String isbn = scanner.nextLine();
        printer.printLine("Year:");
        int releaseDate = getInt();
        printer.printLine("Pages:");
        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Title:");
        String title = scanner.nextLine();
        printer.printLine("Publisher:");
        String publisher = scanner.nextLine();
        printer.printLine("Language:");
        String language = scanner.nextLine();
        printer.printLine("Year:");
        int year = getInt();
        printer.printLine("Month");
        int month = getInt();
        printer.printLine("Day:");
        int day = getInt();

        return new Magazine(title, publisher, language, year, month, day);
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
