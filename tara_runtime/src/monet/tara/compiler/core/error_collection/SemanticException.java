package monet.tara.compiler.core.error_collection;

public class SemanticException extends Exception {

    SemanticError data = new SemanticError();

    public SemanticException(SemanticError data) {
        this.data = data;
    }

    public SemanticError getData() {
        return data;
    }
}
