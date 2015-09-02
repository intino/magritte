package tara.magritte;

import static java.util.stream.Collectors.toList;

public class Case extends Node {

    public Case() {
        this("");
    }

    public Case(String name) {
        super(name);
    }

    public Case(String name, Case aCase, Case owner) {
        super(name);
        this.owner = owner;
        this.types.addAll(aCase.types);
        this.facets.addAll(aCase.facets.stream().map((morph) -> cloneMorph(morph, aCase)).collect(toList()));
        aCase.components().forEach(c -> facets.forEach(m -> m._add(new Case(name + "." + c.shortName(), c, this))));
        PersistenceManager.registerClone(aCase.name, aCase, this);
    }

}
