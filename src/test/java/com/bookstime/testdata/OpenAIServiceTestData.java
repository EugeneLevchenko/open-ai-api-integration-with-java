package com.bookstime.testdata;

import com.bookstime.dto.OpenAIRequest;
import com.bookstime.dto.OpenAIResponse;
import com.bookstime.enums.Role;
import com.bookstime.model.Choice;
import com.bookstime.model.Message;
import com.bookstime.model.Usage;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.web.client.HttpClientErrorException.*;
import java.util.Collections;
import java.util.stream.Stream;
import static com.bookstime.constants.Base.END_PROMPT_PART;
import static com.bookstime.constants.Base.START_PROMPT_PART;
import static com.bookstime.constants.ExceptionsAndLogs.SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE;

public class OpenAIServiceTestData {

    public static final String url = "url";
    public static final String model = "model";

    public static final String draft1 = "draft1";
    public static final String draft2 = "some user's email";
    public static final String draft3 = "Here’s an update: Income spreadsheets: represent only income accounts.";
    public static final String draft4 = "email123456";

    public static final String expected1 = "expected1";
    public static final String expected2 = "expected2";
    public static final String expected3 = "updated draft";
    public static final String expected4 = "modified draft";

    public static final OpenAIRequest openAIRequest1 = new OpenAIRequest(model, getPrompt(draft1));
    public static final OpenAIRequest openAIRequest2 = new OpenAIRequest(model, getPrompt(draft2));
    public static final OpenAIRequest openAIRequest3 = new OpenAIRequest(model, getPrompt(draft3));
    public static final OpenAIRequest openAIRequest4 = new OpenAIRequest(model, getPrompt(draft4));

    public static final OpenAIResponse openAIResponse1 = new OpenAIResponse(null, null, 0, null,
            Collections.singletonList(new Choice(0, new Message(null, expected1), null)), null);
    public static final OpenAIResponse openAIResponse2 = new OpenAIResponse("id", "json", 0, null,
            Collections.singletonList(new Choice(0, new Message(null, expected2), null)), null);
    public static final OpenAIResponse openAIResponse3 = new OpenAIResponse("id", "json", 0, "some model",
            Collections.singletonList(new Choice(123, new Message(null, expected3), "stoped")), new Usage(10, 20, 30));
    public static final OpenAIResponse openAIResponse4 = new OpenAIResponse("id", "json", 123456789, "some model2",
            Collections.singletonList(new Choice(1235453535, new Message(Role.user.name(), expected4), "stoped")),
            new Usage(1023123, 201223, 31231230));

    public static final Class<Unauthorized> exception1 = Unauthorized.class;
    public static final Class<BadRequest> exception2 = BadRequest.class;
    public static final Class<NotFound> exception3 = NotFound.class;
    public static final Class<Forbidden> exception4 = Forbidden.class;

    public static Stream<Arguments> improveDraftValidTestArgs() {
        return Stream.of(
                Arguments.arguments(openAIRequest1, openAIResponse1, draft1, expected1),
                Arguments.arguments(openAIRequest2, openAIResponse2, draft2, expected2),
                Arguments.arguments(openAIRequest3, openAIResponse3, draft3, expected3),
                Arguments.arguments(openAIRequest4, openAIResponse4, draft4, expected4)
        );
    }

    public static Stream<Arguments> improveDraftInvalidTestArgs() {
        return Stream.of(
                Arguments.arguments(openAIRequest1, draft1, SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE, exception1),
                Arguments.arguments(openAIRequest2, draft2, SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE, exception2),
                Arguments.arguments(openAIRequest3, draft3, SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE, exception3),
                Arguments.arguments(openAIRequest4, draft4, SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE, exception4)
        );
    }

    private static String getPrompt(String draft) {
        return new StringBuilder(START_PROMPT_PART).append(System.lineSeparator()).append(draft).append(System.lineSeparator()).append(END_PROMPT_PART).toString();
    }

}
