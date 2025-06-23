package com.example.springAiDemo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GenAiController {

  private SpringAiService springAiService;
  
  @Autowired
 private ImageService imageservice;
  
  public GenAiController(SpringAiService springAiService) {
	  this .springAiService=springAiService;
  }
  
  @GetMapping("ask-ai")
  public String getResponse(@RequestParam String prompt) {
	  return springAiService.getResponse(prompt);
  }
  
  @GetMapping("genrate-image")
  public List<String> genrateImages(HttpServletResponse response,@RequestParam String prompt,
		  @RequestParam (defaultValue ="hd")String quality,
				  @RequestParam (defaultValue="1")int n,
				  @RequestParam (defaultValue="1024")int width,
				  @RequestParam (defaultValue="1024")int height) throws IOException {
	  ImageResponse imageresponse=imageservice.genrateImage(prompt,quality,n,width,height);
//	  String url =imageresponse.getResult().getOutput().getUrl();
//	  response.sendRedirect(url);
	  
	  List<String> imageurl=imageresponse.getResults().stream()
			  .map(result->result.getOutput().getUrl()).collect(Collectors.toList());
	  return imageurl;
  }
  
	
}
