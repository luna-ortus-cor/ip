package miku;

import java.util.Scanner;

public class ChatInstance {
    private Chatbot chatbot;

    public ChatInstance() {
        this.chatbot = new Chatbot();
    }

    public void chat() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Constants.INDENT + "Yahallo! I'm Miku! ^~^");
        while (true) {
            String in = sc.nextLine();
            if (in.trim().equals("exit")) {
                System.out.println(Constants.INDENT + "Miku says bye! :3");
                break;
            }
            String response = this.chatbot.getResponse(0, in);
            System.out.println(Constants.INDENT + response);
        }
    }
}
