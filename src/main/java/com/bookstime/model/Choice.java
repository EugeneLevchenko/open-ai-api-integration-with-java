package com.bookstime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Choice {

    int index;
    Message message;
    String finishReason;

    public Choice(@JsonProperty("index") int index,
                  @JsonProperty("message") Message message,
                  @JsonProperty("finish_reason") String finishReason) {
        this.index = index;
        this.message = message;
        this.finishReason = finishReason;
    }
}
