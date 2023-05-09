package com.ggi.payload.dto;

import java.util.ArrayList;

public class RootPredictionDto {
    private ArrayList<String> roots;
    private String mainFolder;
    public RootPredictionDto(ArrayList<String> roots, String mainFolder){
        this.roots = roots;
        this.mainFolder = mainFolder;
    }

    public ArrayList<String> getRoots() {
        return roots;
    }

    public void setRoots(ArrayList<String> roots) {
        this.roots = roots;
    }

    public String getMainFolder() {
        return mainFolder;
    }

    public void setMainFolder(String mainFolder) {
        this.mainFolder = mainFolder;
    }
}
