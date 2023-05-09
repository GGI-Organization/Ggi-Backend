package com.ggi.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OCRRes {
    private String language;
    private int textAngle;
    private String orientation;
    private OCRRegionRes[] regions;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTextAngle() {
        return textAngle;
    }

    public void setTextAngle(int textAngle) {
        this.textAngle = textAngle;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public OCRRegionRes[] getRegions() {
        return regions;
    }

    public void setRegions(OCRRegionRes[] regions) {
        this.regions = regions;
    }
}
