package io.file;

import exception.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;

public class FileManagerBuilder {

    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build() {
        printer.printLine("Choose data type:");
        FileType fileType = getFileType();
        switch (fileType){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("No such file type: " + fileType);
        }
    }

    private FileType getFileType() {

        boolean typeOk = false;
        FileType result = null;

        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("You have chosen not supported data type, please try again!");
            }
        } while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType type : FileType.values()) {
            printer.printLine(type.name());
        }
    }
}
