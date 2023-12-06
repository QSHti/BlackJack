public class Card {
    private String suit; // e.g., "Hearts", "Spades"
    private String value; // e.g., "A", "2", "10", "K"
    private int rank; // Numeric value for comparison, e.g., 1 for Ace, 11 for Jack

    public Card(String suit, String value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return value + " of " + suit + " (Rank: " + rank + ")";
    }
}
