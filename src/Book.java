public class Book extends Resource {
    private int pages;

    public Book(String isbn, String title, String author, int pages) {
        super(isbn, title, author);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }
}
