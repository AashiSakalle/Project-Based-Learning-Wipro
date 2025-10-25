import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class to represent a playing Card with symbol and number.
 */
class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }
}

public class CardGroupCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Number of Cards : ");
        int numCards;
        try {
            numCards = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for the card count.");
            scanner.close();
            return;
        }

        // Use a TreeMap to store card groups. TreeMap automatically sorts keys (symbols) alphabetically.
        // Key: Card Symbol (String)
        // Value: List of Card objects with that symbol
        Map<String, List<Card>> cardGroups = new TreeMap<>();
        
        // --- Card Data Collection ---
        
        for (int i = 1; i <= numCards; i++) {
            System.out.println(i); // Echo input number as per sample
            
            System.out.print("Enter card " + i + ": ");
            String symbol = scanner.nextLine().trim();

            System.out.print("Enter card " + i + ": ");
            int number;
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered. Skipping this card.");
                // Decrement i to re-read this card index, or just continue if skipping is acceptable
                // Based on sample, we'll continue to keep the count consistent.
                continue; 
            }

            Card newCard = new Card(symbol, number);

            // Group the card by symbol
            // 1. Get the list for the current symbol, or create a new list if it doesn't exist.
            List<Card> cardsWithSymbol = cardGroups.getOrDefault(symbol, new ArrayList<>());
            
            // 2. Add the new card to the list.
            cardsWithSymbol.add(newCard);
            
            // 3. Put the list back into the map (required if it was a new list, harmless otherwise).
            cardGroups.put(symbol, cardsWithSymbol);
        }

        scanner.close();
        
        // --- Output Display ---

        System.out.println("\nDistinct Symbols are:");
        // Print distinct symbols (keys of the map)
        System.out.println(String.join(" ", cardGroups.keySet()));

        System.out.println(); // Blank line for formatting

        // Iterate through the map, which is sorted alphabetically by symbol (due to TreeMap)
        for (Map.Entry<String, List<Card>> entry : cardGroups.entrySet()) {
            String symbol = entry.getKey();
            List<Card> cards = entry.getValue();
            
            int numberOfCards = cards.size();
            int sumOfNumbers = 0;

            System.out.println("Cards in " + symbol + " Symbol");

            // Calculate sum of numbers and print card details
            for (Card card : cards) {
                System.out.println(card.getSymbol() + card.getNumber());
                sumOfNumbers += card.getNumber();
            }

            // Print statistics
            System.out.println("Number of cards: " + numberOfCards);
            System.out.println("Sum of Numbers: " + sumOfNumbers);
            System.out.println();
        }
    }
}