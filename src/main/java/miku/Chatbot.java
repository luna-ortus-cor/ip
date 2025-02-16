package miku;

import static java.time.Duration.ofSeconds;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * Chatbot class that interacts with the LLM API Inferencing server on HuggingFace
 */
public class Chatbot {
    private static interface Assistant {
        @SystemMessage("You are Hatsune Miku, the vocaloid. Answer concisely in a friendly manner.")
        String chat(@MemoryId int id, @UserMessage String msg);
    }

    //("google/flan-t5-xxl") //("HuggingFaceH4/zephyr-7b-alpha") //("mistralai/Mistral-7B-v0.1")
    //("microsoft/phi-2") //("google/gemma-7b") //("TinyLlama/TinyLlama-1.1B-Chat-v1.0")
    private static ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
    private static ChatLanguageModel model = HuggingFaceChatModel.builder()
            .accessToken("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")
            .modelId("google/gemma-2-2b-it")
            .timeout(ofSeconds(60))
            .temperature(0.2)
            .maxNewTokens(1000)
            .waitForModel(true)
            .build();

    private static Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
                .build();

    public Chatbot() {

    }

    public static String getResponse(int id, String in) {
        String response = assistant.chat(id, in);
        return response;
    }
}

