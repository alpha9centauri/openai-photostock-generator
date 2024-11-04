package com.example.PhotoStockGenerator;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
public class GenAIController {
    ChatService chatService;
    ImageService imageService;

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
//        return "Hello ask-ai";
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
//        return "Hello ask-ai-options";
    }

//    @GetMapping("generate-image")
//    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//        ImageResponse imageResponse = imageService.generateImage(prompt);
//        String imageUrl = imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(imageUrl);
//    }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") Integer width,
                                       @RequestParam(defaultValue = "1024") Integer height
    ) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, width, height, n);
        List<String> imageUrls = imageResponse.getResults().stream().map(result -> result.getOutput().getUrl()).toList();

//        List<String> imageUrls = Arrays.asList(
//                "https://i.postimg.cc/DZk95mLr/mess1.jpg",
//                "https://i.postimg.cc/ZnjXTcgH/mess2.webp",
//                "https://i.postimg.cc/cHRVzZBV/mess3.jpg",
//                "https://i.postimg.cc/vTGpFjPD/mess4.jpg"
//        );

        return imageUrls;
    }
}
