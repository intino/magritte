package tara.magritte;


public class Reference {

    private final String qn;
    private Node node;

    public Reference(String qn) {
        this.qn = qn;
    }


    public Node node() {
        if (node == null)
            node = PersistenceManager.loadNode(qn);
        return node;
    }

}
