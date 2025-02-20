package miku;

/**
 * Class to handle chat instances with the user
 */
public class ChatInstance {
    private Chatbot chatbot;

    /**
     * Initializes a new Chat instance.
     */
    public ChatInstance() {
        this.chatbot = new Chatbot();
    }

    /**
     * Starts chat with LLM.
     */
    public void chat() {
        System.out.println(Constants.INDENT + "Yahallo! I'm Miku! ^~^");
        System.out.println();
        while (true) {
            String in = Constants.buildInputString();
            if (in.trim().equals("exit")) {
                System.out.println(Constants.INDENT + "Miku says bye! :3");
                System.out.println();
                break;
            }
            String response = this.chatbot.getResponse(0, in);
            System.out.println(Constants.INDENT + response);
            System.out.println();
        }
    }
}
