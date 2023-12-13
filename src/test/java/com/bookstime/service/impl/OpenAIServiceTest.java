package com.bookstime.service.impl;

import com.bookstime.dto.OpenAIRequest;
import com.bookstime.dto.OpenAIResponse;
import com.bookstime.service.impl.OpenAIService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import static com.bookstime.testdata.OpenAIServiceTestData.model;
import static com.bookstime.testdata.OpenAIServiceTestData.url;
import static org.mockito.ArgumentMatchers.any;

public class OpenAIServiceTest {

    private final RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
    private final OpenAIService cut = new OpenAIService();

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(cut, "restTemplate", restTemplateMock);
        ReflectionTestUtils.setField(cut, "url", url);
        ReflectionTestUtils.setField(cut, "model", model);
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("com.bookstime.testdata.OpenAIServiceTestData#improveDraftValidTestArgs")
    void improveDraftValidTest(OpenAIRequest openAIRequest, OpenAIResponse openAIResponse, String draft, String expected) {
        Mockito.when(restTemplateMock.postForObject(url, openAIRequest, OpenAIResponse.class)).thenReturn(openAIResponse);

        String actual = cut.improveDraft(draft);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(restTemplateMock, Mockito.only()).postForObject(url, openAIRequest, OpenAIResponse.class);
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("com.bookstime.testdata.OpenAIServiceTestData#improveDraftInvalidTestArgs")
    void improveDraftInvalidTest(OpenAIRequest openAIRequest, String draft, String expected, Class<Exception> exception) {
        Mockito.when(restTemplateMock.postForObject(url, openAIRequest, OpenAIResponse.class)).thenThrow(exception);

        String actual = cut.improveDraft(draft);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(restTemplateMock, Mockito.never()).postForObject(any(), any(), any());
    }
}
