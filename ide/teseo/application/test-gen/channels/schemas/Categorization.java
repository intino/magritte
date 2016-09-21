package channels.schemas;

public class Categorization {

    private String name = "";
    private String label = "";
    private java.util.List<Category> categoryList = new java.util.ArrayList<>();

    public String name() {
        return this.name;
    }

    public String label() {
        return this.label;
    }

    public java.util.List<Category> categoryList() {
        return this.categoryList;
    }

    public Categorization name(String name) {
        this.name = name;
        return this;
    }

    public Categorization label(String label) {
        this.label = label;
        return this;
    }

    public Categorization categoryList(java.util.List<Category> categoryList) {
        this.categoryList = categoryList;
        return this;
    }

}