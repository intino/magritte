package magritte.io;

import magritte.Node;
import magritte.Reference;
import magritte.Set;
import magritte.primitives.Date;
import magritte.primitives.Resource;
import magritte.schema.GateReference;
import magritte.schema.Stash;
import magritte.schema.ListSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;


import static magritte.Node.Member.Component;
import static magritte.schema.Stash.*;

@SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
public class StashWriter {

    private final Stash stash;
    private final Node node;

    private final Map<Node, String> types;
    private final Map<String, Pass> fanout;
    private final List<Resource> resources;

    public static Stash stashOf(Node node) {
        return new StashWriter(node).write();
    }

    private StashWriter(Node node) {
        this.stash = new Stash();
        this.node = node;
        this.types = new HashMap<>();
        this.fanout = new HashMap<>();
        this.resources = new ArrayList<>();

    }

    private Stash write() {
        stash.root = rootFrom(node);
        stash.resources = resources();
        return stash;
    }


    private Stash.Root rootFrom(Node node) {
        Stash.Root result = new Stash.Root();
        result.name = node.name();
        result.types = typesFrom(typesOf(node));
        result.fanOut = passesFrom(fanOut(node));
        result.variables = variablesFrom(node);
        result.components = casesFrom(componentsOf(node));
        result.fanIn = passesFrom(fanIn(node));
        return result;
    }

    private Variable[] variablesFrom(Node node) {
        List<Variable> variables = new ArrayList<>();
        for (String var : node.vars()) {
            if (var.startsWith("-")) continue;
            variables.add(create(var, node.get(var)));
        }
        return variables.size() > 0 ? variables.toArray(new Variable[variables.size()]) : null;
    }

    private Variable create(String name, Object value) {
        Variable variable = new Variable();
        variable.name = name;
        variable.value = write(value);
        return variable;
    }

    private Object write(Object value) {
        if (value instanceof Date) return write((Date) value);
        if (value instanceof Date[]) return write((Date[]) value);
        if (value instanceof Resource) return write((Resource) value);
        if (value instanceof Resource[]) return write((Resource[]) value);
        if (value instanceof Reference) return write((Reference) value);
        if (value instanceof Reference[]) return write((Reference[]) value);
        return value;
    }


    private java.util.Date write(Date date) {
        return new java.util.Date(date.timestamp());
    }

    private java.util.Date[] write(Date[] references) {
        return ListSet.cast(references).map(this::write).asList().toArray(new java.util.Date[0]);
    }

    private Blob write(Resource resource) {
        resources.add(resource);
        Blob blob = new Blob();
        blob.uid = GateReference.of(node, resources.size() + "." + resource.format()).toString();
        return blob;
    }

    private Blob[] write(Resource[] resources) {
        Collections.addAll(this.resources, resources);
        return ListSet.cast(resources).map(this::write).asList().toArray(new Blob[0]);
    }

    private Pass write(Reference reference) {
        return fanout.get(reference.value());
    }

    private Pass[] write(Reference[] references) {
        return ListSet.cast(references).map(this::write).asList().toArray(new Pass[0]);
    }

    private String[] typesFrom(Set<Node> types) {
        return types.size() != 0 ? types.map(this::typeFrom).asList().toArray(new String[0]) : null;
    }

    private String typeFrom(Node node) {
        if (node == null) return null;
        if (types.containsKey(node)) return types.get(node);
        return node.name();
    }

    private Case[] casesFrom(Set<Node> nodes) {
        return nodes.size() != 0 ? nodes.map(this::caseFrom).asList().toArray(new Case[0]) : null;
    }

    private Case caseFrom(Node node) {
        Case result = new Case();
        result.name = node.name();
        result.types = typesFrom(typesOf(node));
        result.variables = variablesFrom(node);
        result.components = casesFrom(componentsOf(node));
        result.fanIn = passesFrom(fanIn(node));
        return result;
    }

    private Pass[] passesFrom(Set<Node> nodes) {
        return nodes.size() != 0 ? nodes.map(this::passFrom).asList().toArray(new Pass[0]) : null;
    }

    private Pass passFrom(Node node) {
        Pass pass = new Pass();
        pass.type = node.type().title();
        pass.uid = GateReference.of(node).toString();
        fanout.put(node.ref().value(), pass);
        return pass;
    }

    private Set<Node> typesOf(Node node) {
        return node.types();
    }

    private Set<Node> componentsOf(Node node) {
        return node.members(Component);
    }

    private Set<Node> fanOut(Node node) {
        return node.fanOut();
    }

    private Set<Node> fanIn(Node node) {
        return node.fanIn();
    }

    private Resource[] resources() {
        return resources.toArray(new Resource[resources.size()]);
    }



}
