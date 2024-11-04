package com.example.PhotoStockGenerator;

import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;


    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, Integer width, Integer height, Integer num) {
//        ImageResponse imageResponse = openAiImageModel.call(
//                new ImagePrompt(prompt)
//        );

        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withQuality(quality)
                                .withModel("dall-e-2")
                                .withN(num)
                                .withHeight(height)
                                .withWidth(width).build())
        );
        return imageResponse;
    }
}
