package channels.schemas;

public class Category {

    private String name = "";
    private String label = "";

    public String name() {
        return this.name;
    }

    public String label() {
        return this.label;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public Category label(String label) {
        this.label = label;
        return this;
    }

}