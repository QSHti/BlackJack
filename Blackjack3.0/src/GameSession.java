import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private final long gameSessionId;
    public List<Turn> turns;

    public GameSession(long gameSessionId) {
        this.gameSessionId = gameSessionId;
        this.turns = new ArrayList<>();
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    public long getSessionId() {
        return gameSessionId;
    }

    public boolean isFaulty() {
        // Assuming a session is faulty if any turn is faulty
        for (Turn turn : turns) {
            if (turn.isFaulty()) {
                return true;
            }
        }
        return false;
    }

    public List<Turn> getFaultyTurns() {
        List<Turn> faultyTurns = new ArrayList<>();
        for (Turn turn : turns) {
            if (turn.isFaulty()) {
                faultyTurns.add(turn);
            }
        }
        return faultyTurns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GameSession ID: ").append(gameSessionId).append("\n");
        for (Turn turn : turns) {
            builder.append(turn.toString()).append("\n");
        }
        return builder.toString();
    }
}
