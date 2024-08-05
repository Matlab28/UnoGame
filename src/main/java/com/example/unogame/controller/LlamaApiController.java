package com.example.unogame.controller;

import com.example.unogame.service.LlamaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LlamaApiController {

    private final LlamaApiService llamaApiService;

    @Autowired
    public LlamaApiController(LlamaApiService llamaApiService) {
        this.llamaApiService = llamaApiService;
    }

    @PostMapping("/call-llama")
    public Mono<String> callLlamaApi(@RequestBody LlamaApiRequest request) {
        return llamaApiService.callLlamaApi(request.getInput());
    }

    private static class LlamaApiRequest {
        private String input;

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }
    }
}
