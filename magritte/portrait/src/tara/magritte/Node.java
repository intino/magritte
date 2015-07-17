package tara.magritte;

public interface Node {

    enum Member {
        Component(0), Required(1), Optional(2), Aggregable(3);

        public final int index;
        Member(int index) {
            this.index = index;
        }
    }


    interface LinkAction {
        void as(Tag.Link link);
    }

    String TITLE = "-title";
    String KEY = "-alias";
    String FLAGS = "-flags";

    Reference ref();
    String name();
    String title();
    String key();

    Tag[] tags();
    boolean is(Tag tag);
    <T extends Node> T as(Class<T> class_);

    String[] vars();
    <T> T get(String name);

    Node type();
    Set<Node> types();

    Node parent();
    Set<Node> children();

    Node root();
    Node owner();
    Set<Node> members(Member member);

    Set<Node> fanIn();
    Set<Node> fanOut();

    void set(String name);
    void set(Tag... tags);
    <T> void set(String name, T value);

    LinkAction link(Node node);
    LinkAction unlink(Node node);

    Graph graph();


}
