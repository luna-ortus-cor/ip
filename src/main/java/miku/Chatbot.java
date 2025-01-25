import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class Chatbot {
    interface Assistant{
        String chat(String msg);
    }

    public static void main(String[] args){
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        ChatLanguageModel model = HuggingFaceChatModel.builder()
                .accessToken("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")
                .modelId("google/gemma-2-2b-it")
                .build(); 
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemory(chatMemory)
                .build();

        String answer = assistant.chat("Hello! My name is Miku.");
        System.out.println(answer); // Hello Klaus! How can I assist you today?
    }
}

