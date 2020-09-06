package app;

import io.DataReader;
import model.Book;

class Library {

    public static void main(String[] args) {
        final String appName = "Library v0.8";
        Book[] books = new Book[1000];
        DataReader dataReader = new DataReader();

        books[0] = dataReader.readAndCreateBook();
        books[1] = dataReader.readAndCreateBook();
        dataReader.close();

        System.out.println(appName);
        System.out.println("Available books:");
        books[0].printInfo();
        books[1].printInfo();

    }
}
