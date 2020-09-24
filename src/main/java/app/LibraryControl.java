package app;

import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import exception.NoSuchOptionException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import model.Book;
import model.Library;
import model.Magazine;
import model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();

        try {
            library = fileManager.importData();
            printer.printLine("Data imported from file");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("New database initialized");
            library = new Library();
        }
    }

    void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                default:
                    printer.printLine("There is no such option, try again!");
            }
        } while (option != Option.EXIT);
    }

    private void deleteMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("Magazine deleted");
            } else {
                printer.printLine("Couldn't find magazine");
            }
        } catch (InputMismatchException e){
            printer.printLine("Couldn't create a magazine, wrong data!");
        }
    }

    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("Book deleted");
            } else {
                printer.printLine("Couldn't find book");
            }
        } catch (InputMismatchException e){
            printer.printLine("Couldn't create a book, wrong data!");
        }
    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Couldn't create magazine, wrong data!");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void exit() {

        try {
            fileManager.exportData(library);
            printer.printLine("Data export successful");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Bye! Bye!");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Couldn't create book, wrong data!");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printOptions() {
        printer.printLine("Choose option:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Entered value is not int, try again!");
            }
        }
        return option;
    }

    private enum Option {

        EXIT(0, " - exit"),
        ADD_BOOK(1, " - add new book"),
        ADD_MAGAZINE(2, " - add new magazine"),
        PRINT_BOOKS(3, " - print info about all books"),
        PRINT_MAGAZINES(4, " - print info about all magazines"),
        DELETE_BOOK(5, " - delete book"),
        DELETE_MAGAZINE(6, " - delete magazine");

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
}
