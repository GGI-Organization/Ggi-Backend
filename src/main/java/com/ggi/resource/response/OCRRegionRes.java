package com.ggi.resource.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OCRRegionRes {
    private String boundingBox;
    private OCRLineRes[] lines;

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public OCRLineRes[] getLines() {
        return lines;
    }

    public void setLines(OCRLineRes[] lines) {
        this.lines = lines;
    }
}
