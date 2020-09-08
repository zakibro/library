package io;

import model.Book;
import model.Magazine;

import java.util.Scanner;

public class DataReader {

    private Scanner scanner = new Scanner(System.in);

    public Book readAndCreateBook() {
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Author:");
        String author = scanner.nextLine();
        System.out.println("Publisher:");
        String publisher = scanner.nextLine();
        System.out.println("ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Year:");
        int releaseDate = getInt();
        System.out.println("Pages:");
        int pages = getInt();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Publisher:");
        String publisher = scanner.nextLine();
        System.out.println("Language:");
        String language = scanner.nextLine();
        System.out.println("Year:");
        int year = getInt();
        System.out.println("Month");
        int month = getInt();
        System.out.println("Day:");
        int day = getInt();

        return new Magazine(title, publisher, language, year, month, day);
    }

    public int getInt(){
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void close() {
        scanner.close();
    }
}
