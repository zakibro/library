package io.file;

import exception.DataExportException;
import exception.DataImportException;
import model.Library;

import java.io.*;

class SerializableFileManager implements FileManager {

    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {

            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("File not found " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Error while reading  " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Wrong data type" + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {

            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("File not found " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Error while saving data to file " + FILE_NAME);
        }
    }
}
