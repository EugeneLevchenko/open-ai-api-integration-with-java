package com.bookstime.dto;

import com.bookstime.model.Choice;
import com.bookstime.model.Usage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.List;

@Value
public class OpenAIResponse {
    String id;
    String object;
    long created;
    String model;
    List<Choice> choices;
    Usage usage;

    public OpenAIResponse(@JsonProperty("id") String id,
                          @JsonProperty("object") String object,
                          @JsonProperty("created") long created,
                          @JsonProperty("model") String model,
                          @JsonProperty("choices") List<Choice> choices,
                          @JsonProperty("usage") Usage usage) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }
}
