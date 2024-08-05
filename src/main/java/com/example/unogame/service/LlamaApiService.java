package com.example.unogame.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LlamaApiService {

    private final WebClient webClient;

    @Value("${llama.api.url}")
    private String llamaApiUrl;

    @Value("${llama.api.token}")
    private String apiToken;

    public LlamaApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(llamaApiUrl).build();
    }

    public Mono<String> callLlamaApi(String input) {
        return webClient.post()
                .uri("/endpoint")
                .header("Authorization", "Bearer " + apiToken)
                .bodyValue(new LlamaApiRequest(input))
                .retrieve()
                .bodyToMono(String.class);
    }

    private static class LlamaApiRequest {
        private String input;

        public LlamaApiRequest(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }
    }
}
