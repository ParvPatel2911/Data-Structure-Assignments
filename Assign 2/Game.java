//Parv Patel 3137681
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Peg> secretCode;
    private ArrayList<Peg> playerGuess;
    private final int MAX_GUESSES = 11;

    public Game() {
        secretCode = generateSecretCode();
        playerGuess = new ArrayList<>();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Mastermind!");
        System.out.println("Try to guess the secret code.");
        for (int i = 1; i <= MAX_GUESSES; i++) {
            System.out.println("Guess #" + i + ":");
            getPlayerGuess(scanner);
            if (isCorrectGuess()) {
                System.out.println("You cracked the code!");
                return;
            } else {
                displayFeedback();
            }
        }
        System.out.println("Sorry, you couldn't guess the code. The system wins!");
    }

    private ArrayList<Peg> generateSecretCode() {
        ArrayList<Peg> code = new ArrayList<>();
        Random random = new Random();
        String[] colors = {"red", "blue", "green", "yellow","black","white"};
        for (int i = 0; i < 4; i++) {
            code.add(new Peg(colors[random.nextInt(colors.length)]));
        }
        return code;
    }

    private void getPlayerGuess(Scanner scanner) {
        playerGuess.clear();
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter color for position " + (i + 1) +":" );
            String color = scanner.next();
            playerGuess.add(new Peg(color));
        }
    }

    private boolean isCorrectGuess() {
        return secretCode.equals(playerGuess);
    }

    private void displayFeedback() {
        int exactMatches = 0;
        int partialMatches = 0;
        ArrayList<Peg> tempSecretCode = new ArrayList<>(secretCode);
        ArrayList<Peg> tempPlayerGuess = new ArrayList<>(playerGuess);

        for (int i = 0; i < 4; i++) {
            if (tempSecretCode.get(i).equals(tempPlayerGuess.get(i))) {
                exactMatches++;
                tempSecretCode.remove(i);
                tempPlayerGuess.remove(i);
            }
        }

        for (Peg guessPeg : tempPlayerGuess) {
            if (tempSecretCode.contains(guessPeg)) {
                partialMatches++;
                tempSecretCode.remove(guessPeg);
            }
        }

        System.out.println("Feedback:");
        System.out.println("x: " + exactMatches);
        System.out.println("o: " + partialMatches);
    }

    public static void main(String[] args) {
        
        Game game = new Game();
        game.play();
    }
}
    

