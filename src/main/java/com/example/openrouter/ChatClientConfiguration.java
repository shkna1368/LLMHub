package com.example.openrouter;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ChatClientConfiguration {

    @Bean
    @Autowired
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
