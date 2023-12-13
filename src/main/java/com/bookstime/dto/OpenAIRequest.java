package com.bookstime.dto;

import com.bookstime.enums.Role;
import com.bookstime.model.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.Collections;
import java.util.List;
import static com.bookstime.constants.Base.*;

@Value
public class OpenAIRequest {

    @JsonProperty("model")
    String model;

    @JsonProperty("messages")
    List<Message> messages;

    @JsonProperty("temperature")
    double temperature;

    @JsonProperty("max_tokens")
    int maxTokens;

    @JsonProperty("frequency_penalty")
    double frequencyPenalty;

    @JsonProperty("presence_penalty")
    double presencePenalty;

    public OpenAIRequest(String model, String prompt) {
        this.model = model;
        this.messages = Collections.singletonList(new Message(Role.user.name(), prompt));
        this.temperature = TEMPERATURE;
        this.maxTokens = MAX_TOKENS;
        this.frequencyPenalty = FREQUENCY_PENALTY;
        this.presencePenalty = PRESENCE_PENALTY;
    }
}
