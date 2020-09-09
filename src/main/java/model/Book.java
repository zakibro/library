package model;

public class Book extends Publication {

    private String author;
    private int pages;
    private String isbn;

    public Book(String title, String author, int year, int pages, String publisher, String isbn) {
        super(title, publisher, year);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void printInfo() {
        String info = this.getTitle() + "; " + this.author + "; "
                + this.getYear() + "; " + this.pages + "; " + this.getPublisher();
        if (this.isbn != null) {
            info += "; " + this.isbn;
        }

        System.out.println(info);

    }
}