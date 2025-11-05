package com.example.openrouter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiClient {

    private final RestClient restClient;

    public GeminiClient( ) {
        this.restClient = RestClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyBZOpekL9SiANIu52FlAmsZLlGKXIv5b7Q")
                .defaultHeader("Content-Type", "application/json")
                .build();

    }


    public String generateContent(String prompt) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> payload = Map.of("contents", List.of(content));
        String requestBody = mapper.writeValueAsString(payload);

/*
        String requestBody = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "%s"
                }
              ]
            }
          ]
        }
        """.formatted(prompt);*/

        return restClient.post()
                .body(requestBody)

                .retrieve()
                .body(String.class);

    }}