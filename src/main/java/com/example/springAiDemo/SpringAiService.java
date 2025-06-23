package com.example.springAiDemo;

import java.util.List;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
public class SpringAiService {

	
	private final ChatModel chatModel ;
	
	public SpringAiService(ChatModel chatModel) {
		this.chatModel=chatModel;
	}
	
	public String getResponse(String prompt) {
		return chatModel.call(prompt);
	}
	
	
	public String getResponseOptions(String prompt) {
		ChatResponse response = chatModel.call(
			    new Prompt(
			        prompt,
			        OllamaOptions.builder()
			            .model(OllamaModel.LLAMA3_1)
			            .stop(List.of("###"))
			            .temperature(0.4)	
			            
			            .build()
			    ));
		return response.getResult().getOutput().getText();
	}
}
