package com.ggi.payload.request;

public class FlowProcessSaveReq {
    private String pathDiagramBPMN;
    private String pathMockupGroup;
    private String name;

    public FlowProcessSaveReq() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathDiagramBPMN() {
        return pathDiagramBPMN;
    }

    public void setPathDiagramBPMN(String pathDiagramBPMN) {
        this.pathDiagramBPMN = pathDiagramBPMN;
    }

    public String getPathMockupGroup() {
        return pathMockupGroup;
    }

    public void setPathMockupGroup(String pathMockupGroup) {
        this.pathMockupGroup = pathMockupGroup;
    }
}
