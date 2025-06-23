package com.example.springAiDemo;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final OpenAiImageModel openAiImage;
	
	public ImageService(OpenAiImageModel openAiImage) {
		this.openAiImage=openAiImage;
	}
	
	public ImageResponse genrateImage(String prompt,String quality,int n,int width,int height) {
		ImageResponse imageresponse=	openAiImage.call(
				new ImagePrompt(prompt,OpenAiImageOptions.builder()
						   .quality(quality)
						   .N(n)
						   .height(height)
						   .width(width)
						   .build())
						
				);
		return imageresponse;
	}
}
 