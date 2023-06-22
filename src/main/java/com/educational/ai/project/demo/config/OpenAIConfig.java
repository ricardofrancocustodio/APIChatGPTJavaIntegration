package com.educational.ai.project.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    @Value("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")
    String openaikey;

    @Bean
    public RestTemplate template(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaikey);

            return execution.execute(request, body);

        });

        return restTemplate;
    }
}
