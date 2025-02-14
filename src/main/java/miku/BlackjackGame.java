package miku;

import java.util.*;

/**
 * Simple implementation of a blackjack game.
 * Logic will hit on 16 or below, stand on 17 or above.
 * Only player may split cards.
 */
public class BlackjackGame {
    private static final Random random = new Random();
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final Map<String, Integer> CARD_VALUES = new HashMap<>();

    public BlackjackGame() {

    }

    static {
        for (int i = 0; i < 9; i++) CARD_VALUES.put(RANKS[i], i + 2);
        CARD_VALUES.put("J", 10);
        CARD_VALUES.put("Q", 10);
        CARD_VALUES.put("K", 10);
        CARD_VALUES.put("A", 11);
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<String>> playerHands = new ArrayList<>();
        ArrayList<String> botHand = new ArrayList<>();
        
        // Deal initial hands
        ArrayList<String> initialHand = new ArrayList<>();
        initialHand.add(drawCard());
        initialHand.add(drawCard());
        playerHands.add(initialHand);

        botHand.add(drawCard());
        botHand.add(drawCard());

        System.out.println("Welcome to Blackjack! ");
        System.out.println("Your hand: " + initialHand + " (Score: " + calculateScore(initialHand) + ")");
        System.out.println("Bot's first card: " + botHand.get(0));
        System.out.println();

        // Process splitting hands dynamically
        Queue<ArrayList<String>> handsQueue = new LinkedList<>(playerHands);
        ArrayList<ArrayList<String>> finalHands = new ArrayList<>();

        while (!handsQueue.isEmpty()) {
            ArrayList<String> currentHand = handsQueue.poll();
            while (currentHand.size() == 2 && currentHand.get(0).equals(currentHand.get(1))) {
                System.out.println("You have a pair! Do you want to (s)plit or (n)o? ");
                System.out.println();
                String splitChoice = Constants.INPUT_STRING_BUILDER();
                if (!splitChoice.equals("s")) {
                    break;
                }

                ArrayList<String> hand1 = new ArrayList<>(List.of(currentHand.get(0), drawCard()));
                ArrayList<String> hand2 = new ArrayList<>(List.of(currentHand.get(1), drawCard()));

                handsQueue.add(hand1);
                handsQueue.add(hand2);
                System.out.println("You split your hand!");
                System.out.println("New Hand 1: " + hand1 + " (Score: " + calculateScore(hand1) + ")");
                System.out.println("New Hand 2: " + hand2 + " (Score: " + calculateScore(hand2) + ")");
                System.out.println();

                // Move to next hand in queue
                currentHand = handsQueue.poll();
            }
            finalHands.add(currentHand);
        }

        // Player's turn for each final hand
        for (int i = 0; i < finalHands.size(); i++) {
            ArrayList<String> hand = finalHands.get(i);
            System.out.println("Playing Hand " + (i + 1) + ": " + hand);
            System.out.println();
            while (true) {
                System.out.println("Do you want to (h)it or (s)tand? ");
                System.out.println();
                String choice = Constants.INPUT_STRING_BUILDER();

                if (choice.equals("h")) {
                    hand.add(drawCard());
                    int playerScore = calculateScore(hand);
                    System.out.println("You drew: " + hand.get(hand.size() - 1));
                    System.out.println("Your hand: " + hand + " (Score: " + playerScore + ")");

                    if (playerScore > 21) {
                        System.out.println("You busted on this hand!");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                } else if (choice.equals("s")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Type 'h' to hit or 's' to stand.");
                    System.out.println();
                }
            }
        }

        // Bot's turn
        System.out.println("Bot's turn...");
        System.out.println("Bot's hand: " + botHand + " (Score: " + calculateScore(botHand) + ")");
        while (calculateScore(botHand) < 17) {
            botHand.add(drawCard());
            System.out.println("Bot drew: " + botHand.get(botHand.size() - 1));
            System.out.println("Bot's hand: " + botHand + " (Score: " + calculateScore(botHand) + ")");
        }
        System.out.println();

        // Determine winner for each hand
        System.out.println("\nFinal Scores:");
        int botScore = calculateScore(botHand);
        System.out.println("Bot: " + botScore);
        System.out.println();
        for (int i = 0; i < finalHands.size(); i++) {
            int playerScore = calculateScore(finalHands.get(i));
            System.out.println("Hand " + (i + 1) + ": " + playerScore);

            if (playerScore > 21) {
                System.out.println("You busted! Bot wins.");
            } else if (botScore > 21 || playerScore > botScore) {
                System.out.println("You win this hand!");
            } else if (playerScore == botScore) {
                System.out.println("It's a tie!");
            } else {
                System.out.println("Bot wins this hand!");
            }
            System.out.println();
        }
    }

    // Draws a random card
    private static String drawCard() {
        return RANKS[random.nextInt(RANKS.length)];
    }

    // Calculates the total score of a hand
    private static int calculateScore(List<String> hand) {
        int total = 0;
        int aceCount = 0;

        for (String card : hand) {
            total += CARD_VALUES.get(card);
            if (card.equals("A")) aceCount++;
        }

        // Convert Aces from 11 to 1 if needed
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }
}
