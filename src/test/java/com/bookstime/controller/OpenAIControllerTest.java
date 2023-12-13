package com.bookstime.controller;

import com.bookstime.dto.ImproveDraftRequest;
import com.bookstime.service.impl.OpenAIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OpenAIControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OpenAIService openAIServiceMock = Mockito.mock(OpenAIService.class);
    private final OpenAIController cut = new OpenAIController();
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(cut, "openAIService", openAIServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(cut).build();
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("com.bookstime.testdata.OpenAIControllerTestData#improveDraftValidTestArgs")
    void improveDraftValidTest(String url, ImproveDraftRequest improveDraftRequest, String expected) {
        Mockito.when(openAIServiceMock.improveDraft(improveDraftRequest.getDraft())).thenReturn(expected);

        mockMvc.perform(post(url)
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(objectMapper.writeValueAsString(improveDraftRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn();

        Mockito.verify(openAIServiceMock, Mockito.only()).improveDraft(improveDraftRequest.getDraft());
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("com.bookstime.testdata.OpenAIControllerTestData#improveDraftInvalidTestArgs")
    void improveDraftInvalidTest(String url, ImproveDraftRequest improveDraftRequest) {
        mockMvc.perform(post(url)
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content(objectMapper.writeValueAsString(improveDraftRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();

        Mockito.verify(openAIServiceMock, Mockito.never()).improveDraft(any());
    }
}
