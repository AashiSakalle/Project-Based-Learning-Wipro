import java.util.ArrayList;
import java.util.List;

public class VideoStore {
    private List<Video> store;

    public VideoStore() {
        store = new ArrayList<>();
    }

    public void addVideo(String name) {
        Video video = new Video(name);
        store.add(video);
        System.out.println("Video \"" + name + "\" added successfully.");
    }

    public void doCheckout(String name) {
        Video video = findVideo(name);
        if (video != null) {
            video.doCheckout();
        } else {
            System.out.println("Video \"" + name + "\" not found in inventory.");
        }
    }

    public void doReturn(String name) {
        Video video = findVideo(name);
        if (video != null) {
            video.doReturn();
        } else {
            System.out.println("Video \"" + name + "\" not found in inventory.");
        }
    }

    public void receiveRating(String name, int rating) {
        Video video = findVideo(name);
        if (video != null) {
            video.receiveRating(rating);
        } else {
            System.out.println("Video \"" + name + "\" not found in inventory.");
        }
    }

    public void listInventory() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(String.format("%-6s | %-20s | %-10s | %-5s", "ID", "Video Name", "CheckedOut", "Rating"));
        System.out.println("-------------------------------------------------------------------");
        for (Video v : store) {
            System.out.println(v);
        }
        System.out.println("-------------------------------------------------------------------");
    }

    private Video findVideo(String name) {
        for (Video v : store) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }
}