package tara.magritte.wraps.variables;

import tara.magritte.Node;
import tara.magritte.NodeWrap;
import tara.magritte.Set;
import tara.magritte.Reference;
import tara.magritte.primitives.Date;
import tara.magritte.primitives.Resource;
import tara.magritte.schema.ListSet;
import tara.magritte.handlers.Casting;
import tara.magritte.primitives.Enumerate;

import static tara.magritte.handlers.Casting.cast;


public class Multiple {

    private final Object value;
    private final Node node;

    public Multiple(Object value, Node node) {
        this.value = value;
        this.node = node;
    }

    public String[] asString() {
        return value != null ? (String[]) value : new String[0];
    }

    public int[] asInteger() {
        return value != null ? (int[]) value : new int[0];
    }

    public double[] asDouble() {
        return value != null ? (double[]) value : new double[0];
    }

    public boolean[] asBoolean() {
        return value != null ? (boolean[]) value : new boolean[0];
    }

    public Date[] asDate() {
        return value != null ? (Date[]) value : new Date[0];
    }

    public Reference[] asReference() {
        return value != null ? (Reference[]) value : new Reference[0];
    }

    public Resource[] asResource() {
        return value != null ? (Resource[]) value : new Resource[0];
    }

    public <T extends Enum> T[] asEnumerate(Enum[] enums) {
        return value != null ? enumerates(enums) : (T[]) new Object[0];
    }

    public <T extends NodeWrap> Set<T> as(Class<T> wrapClass) {
        return value != null ? Casting.cast(nodes((Reference[]) value)).as(wrapClass) : ListSet.empty();
    }

    private <T extends Enum> T[] enumerates(Enum[] enums) {
        int[] data = (int[]) value;
        Enum[] result = new Enum[data.length];
        for (int i = 0; i < data.length; i++) result[i] = Enumerate.enumerate(data[i], enums);
        return (T[]) result;
    }


    private Set<Node> nodes(Reference[] ids) {
        return ListSet.cast(ids).map(this::node);
    }

    private Node node(Reference reference) {
        return node.graph().get(reference.value());
    }

}
