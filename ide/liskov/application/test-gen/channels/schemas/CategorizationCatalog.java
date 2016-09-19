package channels.schemas;

public class CategorizationCatalog {

    private java.util.List<Categorization> categorizationList = new java.util.ArrayList<>();

    public java.util.List<Categorization> categorizationList() {
        return this.categorizationList;
    }

    public CategorizationCatalog categorizationList(java.util.List<Categorization> categorizationList) {
        this.categorizationList = categorizationList;
        return this;
    }

}