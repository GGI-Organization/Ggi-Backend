package com.ggi.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionRes {
    private String id;
    private String project;
    private String iteration;
    private String created;
    private PredictionTagRes[] predictions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIteration() {
        return iteration;
    }

    public void setIteration(String iteration) {
        this.iteration = iteration;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public PredictionTagRes[] getPredictions() {
        return predictions;
    }

    public void setPredictions(PredictionTagRes[] predictions) {
        this.predictions = predictions;
    }
}
