public class VideoGame extends Resource {
    private String platform;

    public VideoGame(String ean, String title, String studio, String platform) {
        super(ean, title, studio);
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
