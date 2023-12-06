import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Adds multiple cards to the hand from an array of card strings.
     * Each card string is expected to be in the format "suit-value-rank".
     *
     * @param cardStrings Array of card strings.
     */
    public void addCards(String[] cardStrings) {
        for (String cardString : cardStrings) {
            String[] parts = cardString.split("-");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid card format: " + cardString);
            }
            String suit = parts[0];
            String value = parts[1];
            int rank = Integer.parseInt(parts[2]);
            Card card = new Card(suit, value, rank);
            cards.add(card);
        }
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            int rank = card.getRank();
            if (rank == 1) { // Assuming 1 represents Ace
                aceCount++;
                score += 11; // Ace is initially counted as 11
            } else if (rank > 10) { // Assuming 11, 12, 13 represent Jack, Queen, King
                score += 10;
            } else {
                score += rank;
            }
        }

        // Adjusting the score if it exceeds 21 and there are aces in the hand
        while (score > 21 && aceCount > 0) {
            score -= 10; // Convert an Ace from 11 to 1
            aceCount--;
        }

        return score;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards); // Returning a copy to preserve encapsulation
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(card.toString()).append("\n");
        }
        return builder.toString();
    }
}
