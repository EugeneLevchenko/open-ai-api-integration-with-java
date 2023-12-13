package com.bookstime.service.impl;

import com.bookstime.dto.OpenAIRequest;
import com.bookstime.dto.OpenAIResponse;
import com.bookstime.service.ImproveDraft;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static com.bookstime.constants.Base.END_PROMPT_PART;
import static com.bookstime.constants.Base.START_PROMPT_PART;
import static com.bookstime.constants.ExceptionsAndLogs.*;

@Service
@Slf4j
public class OpenAIService implements ImproveDraft {

    @Value("${openai.api.url}")
    private String url;

    @Value("${openai.api.model}")
    private String model;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String improveDraft(String draft) {
        OpenAIResponse openAIResponse;
        try {
            String prompt = new StringBuilder(START_PROMPT_PART)
                    .append(System.lineSeparator())
                    .append(draft)
                    .append(System.lineSeparator())
                    .append(END_PROMPT_PART).toString();
            openAIResponse = restTemplate.postForObject(url, new OpenAIRequest(model, prompt), OpenAIResponse.class);
        } catch (HttpClientErrorException e) {
            log.error(SEND_REQUEST_IMPROVE_DRAFT_ERROR_LOG, e.getMessage());
            return SEND_REQUEST_IMPROVE_DRAFT_ERROR_MESSAGE;
        }
        String responseFromOpenAI = openAIResponse.getChoices().get(0).getMessage().getContent();
        log.info(SEND_REQUEST_IMPROVE_DRAFT_SUCCESS_LOG);
        return responseFromOpenAI;
    }
}