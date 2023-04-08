package com.ggi.resource.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionTagRes {
    private double probability;
    private String tagId;
    private String tagName;
    private BoundingBoxRes boundingBox;

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public BoundingBoxRes getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBoxRes boundingBox) {
        this.boundingBox = boundingBox;
    }
}
