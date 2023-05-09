package com.ggi.payload.request;

import java.util.ArrayList;

public class MockupReq {
    private Long userId;
    private ArrayList<MockupDetailReq> mockups;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ArrayList<MockupDetailReq> getMockups() {
        return mockups;
    }

    public void setMockups(ArrayList<MockupDetailReq> mockups) {
        this.mockups = mockups;
    }
}
