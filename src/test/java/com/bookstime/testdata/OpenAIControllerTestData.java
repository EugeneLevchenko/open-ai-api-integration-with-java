package com.bookstime.testdata;

import com.bookstime.dto.ImproveDraftRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static com.bookstime.constants.Base.MAX_DRAFT_SIZE;
import static com.bookstime.constants.Base.MIN_DRAFT_SIZE;
import static com.bookstime.constants.ExceptionsAndLogs.INCORRECT_DRAFT_SIZE_ERROR_MESSAGE;

public class OpenAIControllerTestData {

    public static final String mapping = "/improve-draft";

    public static final String draft1 = RandomStringUtils.random(MIN_DRAFT_SIZE, true, true);
    public static final String draft2 = RandomStringUtils.random(MIN_DRAFT_SIZE + 1, true, true);
    public static final String draft3 = RandomStringUtils.random(MAX_DRAFT_SIZE / 2, true, true);
    public static final String draft4 = RandomStringUtils.random(MAX_DRAFT_SIZE - 1, true, true);
    public static final String draft5 = RandomStringUtils.random(MAX_DRAFT_SIZE, true, true);

    public static final String draft6 = null;
    public static final String draft7 = "                ";
    public static final String draft8 = "";
    public static final String draft9 = "1";
    public static final String draft10 = RandomStringUtils.random(MIN_DRAFT_SIZE - 1, true, true);
    public static final String draft11 = RandomStringUtils.random(MAX_DRAFT_SIZE + 1, true, true);

    public static final String expected1 = "expected result";
    public static final String expected2 = INCORRECT_DRAFT_SIZE_ERROR_MESSAGE;

    public static Stream<Arguments> improveDraftValidTestArgs() {
        return Stream.of(
                Arguments.arguments(mapping, new ImproveDraftRequest(draft1), draft1, expected1),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft2), draft2, expected1),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft3), draft3, expected1),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft4), draft4, expected1),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft5), draft5, expected1)
        );
    }

    public static Stream<Arguments> improveDraftInvalidTestArgs() {
        return Stream.of(
                Arguments.arguments(mapping, new ImproveDraftRequest(draft6), expected2),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft7), expected2),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft8), expected2),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft9), expected2),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft10), expected2),
                Arguments.arguments(mapping, new ImproveDraftRequest(draft11), expected2)
        );
    }
}
