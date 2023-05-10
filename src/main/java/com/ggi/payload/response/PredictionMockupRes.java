package com.ggi.payload.response;

import java.util.ArrayList;

public class PredictionMockupRes {
    public ArrayList<MockupRes> mockups;
    private String path;
    public PredictionMockupRes (){}
    public PredictionMockupRes (ArrayList<MockupRes> mockups){
        this.mockups = mockups;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<MockupRes> getMockups() {
        return mockups;
    }

    public void setMockups(ArrayList<MockupRes> mockups) {
        this.mockups = mockups;
    }
}
