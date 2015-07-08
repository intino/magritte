package magritte.editors;

import magritte.*;
import magritte.primitives.Date;
import magritte.primitives.Resource;
import magritte.schema.ListSet;

import java.util.List;

public class VariableEditor {

    protected Node node;

    public VariableEditor(Node node) {
        this.node = node;
    }

    public Node node() {
        return node;
    }

    protected Graph graph() {
        return node.graph();
    }

    public VariableEditor let(String var, NativeCode nativeCode) {
        nativeCode._target(node);
        return setValue(var, nativeCode);
    }

    public VariableEditor set(String var, int value) {
        return setValue(var, value);
    }

    public VariableEditor set(String var, int[] values) {
        return setValue(var, array(var, values));
    }


    public VariableEditor set(String var, double value) {
        return setValue(var, value);
    }

    public VariableEditor set(String var, double[] values) {
        return setValue(var, array(var, values));
    }

    public VariableEditor set(String var, boolean value) {
        return setValue(var, value);
    }

    public VariableEditor set(String var, boolean[] values) {
        return setValue(var, array(var, values));
    }

    public VariableEditor set(String var, String value) {
        return setValue(var, value);
    }

    public VariableEditor set(String var, String[] values) {
        return setValue(var, array(var, values));
    }

    public VariableEditor set(String var, Date date) {
        return setValue(var, date);
    }

    public VariableEditor set(String var, Date[] dates) {
        return setValue(var, array(var, dates));
    }

    public VariableEditor set(String var, Resource resource) {
        return setValue(var, resource);
    }

    public VariableEditor set(String var, Resource[] resources) {
        return setValue(var, array(var, resources));
    }

    public VariableEditor set(String var, Reference reference) {
        ReferenceEditor.of(node).unset(var).set(var, reference);
        return this;
    }

    public VariableEditor set(String var, Reference[] references) {
        ReferenceEditor.of(node).unset(var).set(var, array(var, references));
        return this;
    }

    public VariableEditor set(String var, NodeWrap wrap) {
        ReferenceEditor.of(node).unset(var).set(var, ref(wrap));
        return this;
    }

    public VariableEditor set(String var, NodeWrap[] wraps) {
        ReferenceEditor.of(node).unset(var).set(var, array(var, ref(wraps)));
        return this;
    }

    protected <T> VariableEditor setValue(String var, T value) {
        node.set(var, value);
        return this;
    }

    protected int[] array(String var, int[] values) {
        return values;
    }

    protected double[] array(String var, double[] values) {
        return values;
    }

    protected boolean[] array(String var, boolean[] values) {
        return values;
    }

    protected <T> T[] array(String var, T[] values) {
        return values;
    }

    protected Reference ref(NodeWrap wrap) {
        return wrap._node().ref();
    }

    protected Reference[] ref(NodeWrap[] wraps) {
        List<Reference> references = ListSet.cast(wraps).map(this::ref).asList();
        return references.toArray(new Reference[references.size()]);
    }


}
