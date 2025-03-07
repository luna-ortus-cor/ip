package miku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mental math game
 */
public class MentalMathGame {
    private int difficulty;
    private int length;
    private ArrayList<String> questions;
    private ArrayList<Integer> answers;
    private int score;
    private Ui ui;

    /**
     * Initializes new mental math game instance.
     *
     * @param difficulty int specifying game difficulty
     * @param length int specifying game length
     * @param ui a Ui instance
     */
    public MentalMathGame(int difficulty, int length, Ui ui) {
        this.difficulty = difficulty;
        switch (length) {
        case 1:
            this.length = 10;
            break;
        case 2:
            this.length = 20;
            break;
        case 3:
            this.length = 50;
            break;
        default:
            this.length = 20;
            break;
        };
        this.questions = new ArrayList<String>();
        this.answers = new ArrayList<Integer>();
        this.score = 0;
        this.ui = ui;

        generateQuestions();
    }

    /**
     * Generates questions. Also ensures all answers to questions are integers.
     */
    private void generateQuestions() {
        Random random = new Random();
        int maxDigit = (int) Math.pow(10, difficulty) - 1;

        while (questions.size() < length) {
            int op = random.nextInt(8); // 0 to 7 for operations
            int num1;
            int num2;
            String question = "";
            int answer = 0;

            switch (op) {
            case 0:
                num1 = random.nextInt(maxDigit) + 1;
                num2 = random.nextInt(maxDigit) + 1;
                answer = num1 + num2;
                question = num1 + " + " + num2;
                break;
            case 1:
                num1 = random.nextInt(maxDigit) + 1;
                num2 = random.nextInt(maxDigit) + 1;
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                answer = num1 - num2;
                question = num1 + " - " + num2;
                break;
            case 2:
                num1 = random.nextInt(maxDigit) + 1;
                num2 = random.nextInt(maxDigit) + 1;
                answer = num1 * num2;
                question = num1 + " * " + num2;
                break;
            case 3:
                num2 = random.nextInt(maxDigit) + 1;
                answer = random.nextInt(maxDigit) + 1;
                num1 = answer * num2;
                question = num1 + " / " + num2;
                break;
            case 4:
                num1 = random.nextInt(maxDigit) + 1;
                answer = num1 * num1;
                question = num1 + " ^ 2";
                break;
            case 5:
                num1 = random.nextInt((int) Math.cbrt(maxDigit)) + 1;
                answer = num1 * num1 * num1;
                question = num1 + " ^ 3";
                break;
            case 6:
                num1 = random.nextInt(maxDigit) + 1;
                answer = (int) Math.sqrt(num1);
                num1 = answer * answer;
                question = "\u221A" + num1;
                break;
            case 7:
                num1 = random.nextInt(maxDigit) + 1;
                answer = (int) Math.cbrt(num1);
                num1 = answer * answer * answer; // Ensure perfect cube
                question = "\u221B" + num1;
                break;
            default:
                break;
            }
            questions.add(question);
            answers.add(answer);
        }
    }

    /**
     * Starts the game engine.
     */
    public void startGame() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + questions.get(i));
            System.out.print("Your answer: ");
            System.out.println();
            String in = Constants.buildInputString().toLowerCase();

            if (in.equals("exit")) {
                ui.printGameTerminatedMsg();
                return;
            }

            int userAnswer = Integer.valueOf(in);
            if (userAnswer == answers.get(i)) {
                System.out.println("Correct!");
                System.out.println();
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + answers.get(i));
                System.out.println();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000; // Convert to seconds

        System.out.println("\nGame Over!");
        System.out.println("Your score: " + score + "/" + length);
        System.out.println("Time taken: " + elapsedTime + " seconds");
        System.out.println();
    }
}
