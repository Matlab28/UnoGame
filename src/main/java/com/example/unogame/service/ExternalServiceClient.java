package com.example.unogame.service;

import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient.Builder webClientBuilder,
                                 @Value("${external.service.url}") String serviceUrl) {
        this.webClient = webClientBuilder.baseUrl(serviceUrl).build();
    }

    public Mono<Response> callService(Request request) {
        return webClient.post()
                .uri("/endpoint")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Response.class);
    }
}
