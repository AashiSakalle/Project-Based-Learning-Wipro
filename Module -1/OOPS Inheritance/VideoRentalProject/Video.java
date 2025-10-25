public class Video {
    private String videoName;
    private boolean checkout;
    private int rating;
    private final int videoID; // Auto-generated unique ID
    private static int idCounter = 1000; // Static counter for generating unique IDs

    public Video(String name) {
        this.videoName = name;
        this.checkout = false;
        this.rating = 0;
        this.videoID = idCounter++;
    }

    public String getName() {
        return videoName;
    }

    public int getVideoID() {
        return videoID;
    }

    public void doCheckout() {
        if (!checkout) {
            checkout = true;
            System.out.println("Video \"" + videoName + "\" checked out successfully.");
        } else {
            System.out.println("Video \"" + videoName + "\" is already checked out.");
        }
    }

    public void doReturn() {
        if (checkout) {
            checkout = false;
            System.out.println("Video \"" + videoName + "\" returned successfully.");
        } else {
            System.out.println("Video \"" + videoName + "\" was not checked out.");
        }
    }

    public void receiveRating(int rating) {
        this.rating = rating;
        System.out.println("Rating \"" + rating + "\" has been mapped to the Video \"" + videoName + "\"");
    }

    public int getRating() {
        return rating;
    }

    public boolean getCheckout() {
        return checkout;
    }

    @Override
    public String toString() {
        return String.format("%-6d | %-20s | %-10s | %-5d", videoID, videoName, checkout, rating);
    }
}