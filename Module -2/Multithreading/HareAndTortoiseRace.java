public class HareAndTortoiseRace {

    // Define the race distance in meters
    private static final int RACE_DISTANCE = 100;
    
    // A shared flag to stop the threads once a winner is found
    private static volatile boolean raceFinished = false;

    /**
     * Inner class representing the Hare and Tortoise threads.
     * Implements Runnable to demonstrate thread creation.
     */
    static class Racer implements Runnable {
        private final String name;
        private int distanceCovered = 0;

        public Racer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            // Set the thread name for easy identification (used in the sleep logic)
            Thread.currentThread().setName(name);

            // The variable used to store the distance covered is also used to iterate the loop.
            for (distanceCovered = 0; distanceCovered <= RACE_DISTANCE; distanceCovered++) {
                
                // 1. Check for a winner (thread control mechanism)
                if (raceFinished) {
                    break; // Stop if another thread has won
                }

                // Print the current progress
                System.out.println(name + " has covered " + distanceCovered + " meters.");

                // 2. Hare sleep mechanism (Requirement c)
                // The thread sleeps after covering 60 meters.
                if (name.equals("Hare") && distanceCovered == 60) {
                    try {
                        System.out.println("--- Hare is sleeping for 1000 milliseconds. ---");
                        Thread.sleep(1000); // Pause for 1000 milliseconds
                        System.out.println("--- Hare wakes up! ---");
                    } catch (InterruptedException e) {
                        // Restore the interrupted status
                        Thread.currentThread().interrupt();
                        break;
                    }
                }

                // 3. Check for the winner after incrementing
                if (distanceCovered == RACE_DISTANCE) {
                    // Check if we are the first to finish
                    if (!raceFinished) {
                        raceFinished = true; // Set the flag to stop the other thread
                        System.out.println("\n*** " + name + " WINS THE RACE! ***");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        
        // --- Setup Threads ---
        
        Thread hareThread = new Thread(new Racer("Hare"));
        Thread tortoiseThread = new Thread(new Racer("Tortoise"));

        // --- Set Priorities (Requirement b) ---
        // Hare is faster, so set a higher priority.
        // Use static variables in Thread class: MAX_PRIORITY (10) and NORM_PRIORITY (5).
        hareThread.setPriority(Thread.MAX_PRIORITY); // Higher Priority
        tortoiseThread.setPriority(Thread.NORM_PRIORITY); // Normal Priority

        System.out.println("Starting the Hare and Tortoise Race (Distance: " + RACE_DISTANCE + "m)...");
        System.out.println("Hare Priority: " + hareThread.getPriority());
        System.out.println("Tortoise Priority: " + tortoiseThread.getPriority());
        
        // --- Start Threads (Requirement a) ---
        hareThread.start();
        tortoiseThread.start();

        try {
            // Wait for both threads to complete (or be stopped)
            hareThread.join();
            tortoiseThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nRace has ended.");
    }
}