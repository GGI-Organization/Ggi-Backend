package com.ggi.resource.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OCRLineRes {
    private String boundingBox;
    private OCRWordsRes[] words;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public OCRWordsRes[] getWords() {
        return words;
    }

    public void setWords(OCRWordsRes[] words) {
        this.words = words;
    }
}
