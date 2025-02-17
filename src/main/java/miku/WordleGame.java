package miku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Wordle game class
 */
public class WordleGame {
    private static final String FILE_NAME = Constants.FILEPATH_WORDLIST;
    private int difficulty;
    private int maxGuesses;
    private ArrayList<String> wordList;
    private String targetWord;
    private Ui ui;

    /**
     * Initializes new wordle game instance.
     *
     * @param difficulty int specifying game difficulty
     * @param ui a Ui instance
     */
    public WordleGame(int difficulty, Ui ui) {
        this.difficulty = difficulty;
        this.wordList = loadWordList();
        this.targetWord = selectTargetWord();
        switch(difficulty) {
        case 1:
            this.maxGuesses = 6;
            break;
        case 2:
            this.maxGuesses = 8;
            break;
        case 3:
            this.maxGuesses = 10;
            break;
        default:
            break;
        };
        this.ui = ui;
    }

    /**
     * Load wordlist from wordlist file.
     */
    private ArrayList<String> loadWordList() {
        ArrayList<String> words = new ArrayList<>();
        try (InputStream is = this.getClass().getResourceAsStream(FILE_NAME)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (isWordOfDifficulty(line)) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading word list.");
            System.out.println();
        }
        return words;
    }

    /**
     * Check if word is of correct difficulty.
     *
     * @param word string of the word proposed
     * @return boolean indicating if the proposed word is of correct difficulty
     */
    private boolean isWordOfDifficulty(String word) {
        int length = word.trim().length();
        switch(this.difficulty) {
        case 1:
            return (length >= 5 && length <= 7);
        case 2:
            return (length >= 8 && length <= 10);
        case 3:
            return (length >= 11 && length <= 13);
        default:
            return false;
        }
    }

    /**
     * Select a random word from the wordlist.
     */
    private String selectTargetWord() {
        Random random = new Random();
        String word = "exit";
        while (word.equals("exit")) {
            word = wordList.get(random.nextInt(wordList.size()));
        }
        assert !word.equals("exit");
        return word;
    }

    /**
     * Starts the game engine.
     */
    public void startGame() {
        int guessesUsed = 0;
        boolean guessedCorrectly = false;

        System.out.println("Welcome to Wordle!");
        System.out.println("You have " + maxGuesses + " guesses to find the word.");
        System.out.println("The word has " + targetWord.length() + " letters.");
        System.out.println();

        while (guessesUsed < maxGuesses && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            System.out.println();
            String guess = Constants.buildInputString().toLowerCase();

            if (guess.equals("exit")) {
                ui.printGameTerminatedMsg();
                return;
            }

            if (guess.length() != targetWord.length()) {
                System.out.println("Invalid guess. Your guess must be " + targetWord.length() + " letters long.");
                System.out.println();
                continue;
            }

            if (!wordList.contains(guess)) {
                System.out.println("Invalid guess. The word is not in the word list.");
                System.out.println();
                continue;
            }

            guessesUsed++;
            guessedCorrectly = evaluateGuess(guess);
        }

        if (guessedCorrectly) {
            System.out.println("Congratulations! You guessed the word in " + guessesUsed + " guesses.");
        } else {
            System.out.println("Out of guesses! The correct word was: " + targetWord);
            System.out.println("Better luck next time!");
        }
        System.out.println();
    }

    /**
     * Evaluate the user guess.
     * Also prints the output of the user guess indicating correct letters,
     * correct letters but wrong position, and wrong letters.
     *
     * @param guess string of the user guess
     * @return boolean indicating if the user guess is correct
     */
    private boolean evaluateGuess(String guess) {
        StringBuilder feedback = new StringBuilder();
        boolean[] usedInTarget = new boolean[targetWord.length()];
        boolean[] usedInGuess = new boolean[guess.length()];

        // Check for correct letters in correct positions
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == targetWord.charAt(i)) {
                feedback.append("\u2713 "); // Tick
                usedInTarget[i] = true;
                usedInGuess[i] = true;
            } else {
                feedback.append("\u2715 "); // Cross
            }
        }

        // Check for correct letters in incorrect positions
        for (int i = 0; i < guess.length(); i++) {
            if (!usedInGuess[i] && feedback.charAt(2 * i) != '\u2713') {
                for (int j = 0; j < targetWord.length(); j++) {
                    if (!usedInTarget[j] && guess.charAt(i) == targetWord.charAt(j)) {
                        feedback.setCharAt(2 * i, '\u25CB'); // Circle
                        usedInTarget[j] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(feedback);
        System.out.println();
        return guess.equals(targetWord);
    }
}
