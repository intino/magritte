package happysense.schemas;

public class Today {

    private Count count;

    public Count count() {
        return this.count;
    }

    public Today count(Count count) {
        this.count = count;
        return this;
    }

}