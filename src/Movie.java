public class Movie extends Resource {
    private String director;
    private int duration;

    public Movie(String ean, String title, String studio, String director, int duration) {
        super(ean, title, studio);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }
}
