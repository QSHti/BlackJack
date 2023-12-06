public class Turn {
    private long sessionId;
    private String type; // Example: "P Hit", "D Stand", etc.
    private Hand hand;   // The hand associated with this turn

    public Turn(long sessionId, String type, Hand hand) {
        this.sessionId = sessionId;
        this.type = type;
        this.hand = hand;
    }

    public static Turn parseTurn(String turnString) {
        // Example format: "sessionId,type,cardString1|cardString2"
        String[] parts = turnString.split(",");
        long sessionId = Long.parseLong(parts[0]);
        String type = parts[1];
        String[] cardStrings = parts[2].split("\\|"); // Splitting cards separated by '|'

        Hand hand = new Hand();
        hand.addCards(cardStrings); // Add cards to the hand

        return new Turn(sessionId, type, hand);
    }

    public long getSessionId() {
        return sessionId;
    }

    public boolean isFaulty() {
        if (type.equals("D Hit") && hand.calculateScore() > 21) {
            return true;
        } else if (type.equals("P Hit") && hand.calculateScore() > 21) {
            return true;
        } else return type.equals("D Stand") && hand.calculateScore() < 17;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "sessionId=" + sessionId +
                ", type='" + type + '\'' +
                ", hand=" + hand +
                '}';
    }
}
