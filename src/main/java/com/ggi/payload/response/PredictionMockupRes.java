package com.ggi.payload.response;

import java.util.ArrayList;

public class PredictionMockupRes {
    public ArrayList<MockupRes> mockups;
    public PredictionMockupRes (ArrayList<MockupRes> mockups){
        this.mockups = mockups;
    }

    public ArrayList<MockupRes> getMockups() {
        return mockups;
    }

    public void setMockups(ArrayList<MockupRes> mockups) {
        this.mockups = mockups;
    }
}
