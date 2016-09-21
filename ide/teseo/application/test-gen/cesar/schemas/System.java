package cesar.schemas;

public class System {

    private String ID = "";
    private java.util.List<String> artifacts = new java.util.ArrayList<>();
    private String mainClass = "";
    private String artifactory = "";

    public String ID() {
        return this.ID;
    }

    public java.util.List<String> artifacts() {
        return this.artifacts;
    }

    public String mainClass() {
        return this.mainClass;
    }

    public String artifactory() {
        return this.artifactory;
    }

    public System ID(String ID) {
        this.ID = ID;
        return this;
    }

    public System artifacts(java.util.List<String> artifacts) {
        this.artifacts = artifacts;
        return this;
    }

    public System mainClass(String mainClass) {
        this.mainClass = mainClass;
        return this;
    }

    public System artifactory(String artifactory) {
        this.artifactory = artifactory;
        return this;
    }

}