package channels.schemas;

public class Processes {

    private String name = "";
    private String label = "";

    public String name() {
        return this.name;
    }

    public String label() {
        return this.label;
    }

    public Processes name(String name) {
        this.name = name;
        return this;
    }

    public Processes label(String label) {
        this.label = label;
        return this;
    }

}