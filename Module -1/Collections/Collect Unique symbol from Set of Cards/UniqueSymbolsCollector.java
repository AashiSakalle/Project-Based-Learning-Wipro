import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

public class UniqueSymbolsCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // LinkedHashMap stores unique symbols (key) and the first Card object seen (value).
        // LinkedHashMap preserves insertion order, which is crucial for finding the 'first occurrence'.
        Map<String, Card> uniqueCardsMap = new LinkedHashMap<>();
        
        int totalCardsEntered = 0;
        final int UNIQUE_SYMBOLS_NEEDED = 4;

        System.out.println("Collect Unique Symbols From Set of Cards");
        
        // Loop until four unique symbols have been collected
        while (uniqueCardsMap.size() < UNIQUE_SYMBOLS_NEEDED) {
            System.out.println("Enter a card");
            
            // 1. Get Symbol
            System.out.print("Enter a card symbol: ");
            String symbol = scanner.nextLine().trim();

            // 2. Get Number
            System.out.print("Enter a card number: ");
            int number;
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer for the card number.");
                continue; // Skip this card and continue loop
            }

            // 3. Create Card object
            Card currentCard = new Card(symbol, number);
            totalCardsEntered++;

            // 4. Store unique symbol: Only put the card in the map if the symbol is new.
            if (!uniqueCardsMap.containsKey(symbol)) {
                uniqueCardsMap.put(symbol, currentCard);
            }
        }

        scanner.close();

        // --- Output Generation ---

        System.out.println("\nFour symbols gathered in " + totalCardsEntered + " cards.");
        System.out.println("Cards in Set are");

        // 1. Get the collected cards and put them into a list for sorting
        List<Card> collectedCards = new ArrayList<>(uniqueCardsMap.values());
        
        // 2. Sort the collected cards by symbol in alphabetical order
        Collections.sort(collectedCards, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                // Sorting alphabetically by symbol
                return c1.getSymbol().compareTo(c2.getSymbol());
            }
        });

        // 3. Display the sorted details
        for (Card card : collectedCards) {
            System.out.println(card.getSymbol());
            System.out.println(card.getNumber());
        }
    }
}