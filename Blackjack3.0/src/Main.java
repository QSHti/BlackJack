import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "filePath to game_data.txt";
        String outputFilePath = "filePath to analyzer_results.txt";

        try {
            List<GameSession> gameSessions = readGameSessionsFromFile(inputFilePath);
            List<GameSession> faultySessions = findFaultySessions(gameSessions);
            writeFaultySessionsToFile(faultySessions, outputFilePath);
            System.out.println("Analysis complete. Results written to " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<GameSession> readGameSessionsFromFile(String filePath) throws IOException {
        List<GameSession> gameSessions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        GameSession session = null;
        while ((line = reader.readLine()) != null) {
            Turn turn = Turn.parseTurn(line);
            if (session == null || session.getSessionId() != turn.getSessionId()) {
                session = new GameSession(turn.getSessionId());
                gameSessions.add(session);
            }
            session.addTurn(turn);
        }
        reader.close();
        return gameSessions;
    }

    private static List<GameSession> findFaultySessions(List<GameSession> gameSessions) {
        List<GameSession> faultySessions = new ArrayList<>();
        for (GameSession session : gameSessions) {
            if (isSessionFaulty(session)) {
                faultySessions.add(session);
            }
        }
        return faultySessions;
    }

    private static boolean isSessionFaulty(GameSession session) {
        for (Turn turn : session.turns) {
            if (turn.isFaulty()) {
                // If any turn in the session is faulty, the whole session is considered faulty
                return true;
            }
        }
        // If none of the turns are faulty, the session is not faulty
        return false;
    }

    private static void writeFaultySessionsToFile(List<GameSession> faultySessions, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (GameSession session : faultySessions) {
            writer.write("Faulty Session: " + session.getSessionId());
            writer.newLine();
            for (Turn turn : session.getFaultyTurns()) {
                writer.write(turn.toString());
                writer.newLine();
            }
        }
        writer.close();
    }
}
