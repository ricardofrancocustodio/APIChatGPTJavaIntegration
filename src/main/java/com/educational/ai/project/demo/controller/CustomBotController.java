package com.educational.ai.project.demo.controller;


import com.educational.ai.project.demo.dto.ChatGPTRequest;
import com.educational.ai.project.demo.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/bot")
public class CustomBotController {

    @Value("${openai.api.model}")
    public String model;

    @Value("${openai.api.url}")
    public String url;

    @Autowired
    public RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject("https://api.openai.com/v1/chat/completions",request,ChatGPTResponse.class);


        assert chatGPTResponse != null;
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }

}

