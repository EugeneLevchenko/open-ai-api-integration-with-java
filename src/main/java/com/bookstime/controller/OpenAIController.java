package com.bookstime.controller;

import com.bookstime.dto.ImproveDraftRequest;
import com.bookstime.service.impl.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @CrossOrigin
    @PostMapping(path = "/improve-draft", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String improveDraft(@RequestBody @Valid ImproveDraftRequest improveDraftRequest) {
        return openAIService.improveDraft(improveDraftRequest.getDraft());
    }
}


