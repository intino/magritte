package happysense.schemas;

public class CategorizationCatalogResponseSchema {

    private java.util.List<Categorization> categorizationList = new java.util.ArrayList<>();

    public java.util.List<Categorization> categorizationList() {
        return this.categorizationList;
    }

    public CategorizationCatalogResponseSchema categorizationList(java.util.List<Categorization> categorizationList) {
        this.categorizationList = categorizationList;
        return this;
    }

}