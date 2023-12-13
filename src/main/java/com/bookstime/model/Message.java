package com.bookstime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Message {

    String role;
    String content;

    public Message(@JsonProperty("role") String role,
                   @JsonProperty("content") String content) {
        this.role = role;
        this.content = content;
    }
}
