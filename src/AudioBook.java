public class AudioBook extends Resource {
    private int duration;

    public AudioBook(String isbn, String title, String author, int duration) {
        super(isbn, title, author);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
