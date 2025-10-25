import java.util.Scanner;

public class VideoLauncher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VideoStore store = new VideoStore();
        int choice;

        System.out.println("==================================");
        System.out.println("   Welcome to Video Rental Store  ");
        System.out.println("==================================");

        do {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Add Videos");
            System.out.println("2. Check Out Video");
            System.out.println("3. Return Video");
            System.out.println("4. Receive Rating");
            System.out.println("5. List Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1..6): ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the video you want to add: ");
                    String name = sc.nextLine();
                    store.addVideo(name);
                    break;

                case 2:
                    System.out.print("Enter the name of the video you want to check out: ");
                    name = sc.nextLine();
                    store.doCheckout(name);
                    break;

                case 3:
                    System.out.print("Enter the name of the video you want to return: ");
                    name = sc.nextLine();
                    store.doReturn(name);
                    break;

                case 4:
                    System.out.print("Enter the name of the video you want to rate: ");
                    name = sc.nextLine();
                    System.out.print("Enter the rating for this video (1–10): ");
                    int rating = sc.nextInt();
                    store.receiveRating(name, rating);
                    break;

                case 5:
                    store.listInventory();
                    break;

                case 6:
                    System.out.println("Exiting...!! Thanks for using the application.");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        } while (choice != 6);

        sc.close();
    }
}