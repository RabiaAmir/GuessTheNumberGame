import java.util.Random;
import java.util.Scanner;
 
class Game {
    private int secretNumber;
    private int attempts;
    private int bestScore;
 
    public Game() {
        this.bestScore = Integer.MAX_VALUE; // Initialize bestScore to a high value
    }
 
    private int generateRandomNumber(int range) {
        Random random = new Random();
        return random.nextInt(range) + 1; // generate a random number between 1 and the given range
    }
 
    public void play(int range) {
        this.secretNumber = generateRandomNumber(range);
        this.attempts = 0;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Guess the Number!");
        System.out.println("-----------------------------");
        System.out.println("I'm thinking of a number between 1 and " + range + ".");
        System.out.println("--------------------------------------------");
 
        while (true) {
            System.out.print("Enter your guess: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next(); // clear the invalid input
            }
            int guess = scanner.nextInt();
            attempts++;
 
            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                if (attempts < bestScore) {
                    bestScore = attempts;
                    System.out.println("New high score! Best score: " + bestScore + " attempts.");
                }
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
 
            if (attempts == 5) {
                System.out.println("Hint: The secret number is " + (secretNumber % 2 == 0 ? "even." : "odd."));
            }
        }
 
        System.out.println("The secret number was: " + secretNumber);
    }
 
    public int getBestScore() {
        return bestScore;
    }
 
    public boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.equals("yes");
    }
}
 
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
 
        while (true) {
            System.out.println("Select difficulty level: ");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");
            System.out.print("Enter your choice: ");
 
            int choice = scanner.nextInt();
            int range;
            switch (choice) {
                case 1:
                    range = 50;
                    break;
                case 2:
                    range = 100;
                    break;
                case 3:
                    range = 200;
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Medium (1-100).");
                    range = 100;
            }
 
            game.play(range);
 
            if (!game.playAgain()) {
                System.out.println("Thank you for playing! Your best score was " + game.getBestScore() + " attempts.");
                break;
            }
        }
    }}
