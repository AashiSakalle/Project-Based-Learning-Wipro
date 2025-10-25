import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Class to represent a Box with length, width, and height.
 * The core requirement is that two boxes are equal if their volumes are equal.
 */
class Box {
    private double length;
    private double width;
    private double height;
    private double volume;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = length * width * height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    
    public double getVolume() {
        return volume;
    }

    /**
     * Overrides equals() to compare Box objects based ONLY on their volume.
     * This is crucial for the Set to treat boxes with the same volume as duplicates.
     */
    @Override
    public boolean equals(Object o) {
        // Standard check: same object reference
        if (this == o) return true;
        // Standard check: null or different class
        if (o == null || getClass() != o.getClass()) return false;
        
        // Cast the object to a Box
        Box box = (Box) o;
        
        // Use Double.compare for safe double comparison
        return Double.compare(box.volume, volume) == 0;
    }

    /**
     * Overrides hashCode() to return a hash code based ONLY on the volume.
     * This must be done for the Set (HashSet) to function correctly with the custom equals() method.
     */
    @Override
    public int hashCode() {
        // Generates a hash code based on the volume double value
        return Objects.hash(volume);
    }
    
    /**
     * Helper method to display box details
     */
    @Override
    public String toString() {
        return String.format("Box [L=%.1f, W=%.1f, H=%.1f, Volume=%.1f]", length, width, height, volume);
    }
}

public class SetOfBoxes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of Box: ");
        int numBoxes;
        try {
            numBoxes = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.close();
            return;
        }
        scanner.nextLine(); // Consume the newline

        // Use a Set to automatically handle unique objects (based on volume due to overrides)
        Set<Box> uniqueBoxes = new HashSet<>();

        // Collect details for N boxes
        for (int i = 1; i <= numBoxes; i++) {
            System.out.println("\nEnter the Box " + i + " details");
            
            double length, width, height;

            try {
                System.out.print("Enter Length: ");
                length = scanner.nextDouble();
                
                System.out.print("Enter Width: ");
                width = scanner.nextDouble();
                
                System.out.print("Enter Height: ");
                height = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                
                Box newBox = new Box(length, width, height);

                // Attempt to add the box. Set's add() returns false if the item is already present (i.e., volume is equal).
                if (uniqueBoxes.add(newBox)) {
                    System.out.println("Box inserted into the Set.");
                } else {
                    System.out.println("The Box was NOT inserted into the Set (Volume " + newBox.getVolume() + " is a duplicate of a box already in the Set).");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (Double) for dimensions.");
                scanner.nextLine(); // Clear the buffer
                i--; // Decrement counter to re-enter details for the current box
            }
        }
        
        System.out.println("\n--- Final Set Contents ---");
        if (uniqueBoxes.isEmpty()) {
            System.out.println("The set is empty.");
        } else {
            System.out.println("The final set contains " + uniqueBoxes.size() + " unique boxes (by volume):");
            for (Box box : uniqueBoxes) {
                System.out.println(box);
            }
        }

        scanner.close();
    }
}