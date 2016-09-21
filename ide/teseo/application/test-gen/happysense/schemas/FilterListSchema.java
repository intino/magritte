package happysense.schemas;

public class FilterListSchema {

    private java.util.List<String> filters = new java.util.ArrayList<>();

    public java.util.List<String> filters() {
        return this.filters;
    }

    public FilterListSchema filters(java.util.List<String> filters) {
        this.filters = filters;
        return this;
    }

}