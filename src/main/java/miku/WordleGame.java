package miku;

import java.io.*;
import java.util.*;

public class WordleGame {
    private int difficulty;
    private int maxGuesses;
    private ArrayList<String> wordList;
    private String targetWord;
    private static final String FILE_NAME = Constants.FILEPATH_WORDLIST;

    public WordleGame(int difficulty) {
        this.difficulty = difficulty;
        this.wordList = loadWordList();
        this.targetWord = selectTargetWord();
        switch(difficulty){
            case 1: this.maxGuesses=6;
                    break;
            case 2: this.maxGuesses=8;
                    break;
            case 3: this.maxGuesses=10;
                    break;
        };
    }

    private ArrayList<String> loadWordList() {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (isWordOfDifficulty(line)) {
                    words.add(line);
                }
            }
        } catch (IOException e){
            System.out.println("Error loading word list.");
            System.out.println();
        }
        return words;
    }

    private boolean isWordOfDifficulty(String word) {
        int length = word.trim().length();
        switch(this.difficulty){
            case 1: return(length >= 5 && length <= 7);
            case 2: return(length >= 8 && length <= 10);
            case 3: return(length >= 11 && length <= 13);
            default: return false;
        }
    }

    private String selectTargetWord() {
        Random random = new Random();
        return wordList.get(random.nextInt(wordList.size()));
    }

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
            String guess = Constants.INPUT_STRING_BUILDER().toLowerCase();

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
