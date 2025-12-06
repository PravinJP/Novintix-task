package com.social.novintix.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }
}

