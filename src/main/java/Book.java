class Book {
    String title;
    String author;
    int releaseDate;
    int pages;
    String publisher;
    String isbn;

    public Book(String title, String author, int releaseDate, int pages, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.pages = pages;
        this.publisher = publisher;
        this.isbn = isbn;
    }


    void printInfo() {
        String info = this.title + "; " + this.author + "; "
                + this.releaseDate + "; " + this.pages + "; " + this.publisher + "; " + this.isbn;

        System.out.println(info);

    }
}
