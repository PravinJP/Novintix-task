package com.social.novintix.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UpdatePostRequest {

    private String newContent;

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String text) {
        this.newContent = text;
    }
}
