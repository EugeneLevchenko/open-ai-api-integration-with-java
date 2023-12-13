package com.bookstime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static com.bookstime.constants.Base.MAX_DRAFT_SIZE;
import static com.bookstime.constants.Base.MIN_DRAFT_SIZE;
import static com.bookstime.constants.ExceptionsAndLogs.INCORRECT_DRAFT_SIZE_ERROR_MESSAGE;
import static com.bookstime.constants.ExceptionsAndLogs.NOT_BLANK_DRAFT_MESSAGE;

@Value
public class ImproveDraftRequest {

    @NotBlank(message = NOT_BLANK_DRAFT_MESSAGE)
    @Size(min = MIN_DRAFT_SIZE, max = MAX_DRAFT_SIZE, message = INCORRECT_DRAFT_SIZE_ERROR_MESSAGE)
    String draft;

    public ImproveDraftRequest(@JsonProperty("draft") String draft) {
        this.draft = draft;
    }

}
