package tara.io;

import java.util.List;

public class Helper {

    public static Stash newStash(String language, List<String> uses, List<Concept> concepts, List<Instance> instances){
        Stash stash = new Stash();
        stash.language = language;
        stash.uses.addAll(uses);
        stash.concepts.addAll(concepts);
        stash.instances.addAll(instances);
        return stash;
    }

    public static Concept newConcept(String name, boolean isAbstract, boolean isMetaConcept, boolean isMain, String className, String parent, List<String> types, List<String> allowsMultiple, List<String> allowsSingle, List<String> requiresMultiple, List<String> requiresSingle, List<Prototype> prototypes, List<Variable> variables, List<Instance> instances){
        Concept concept = new Concept();
        concept.name = name;
        concept.isAbstract = isAbstract;
        concept.isMetaConcept = isMetaConcept;
        concept.isMain = isMain;
        concept.className = className;
        concept.parent = parent;
        concept.types.addAll(types);
        concept.allowsMultiple.addAll(allowsMultiple);
        concept.allowsSingle.addAll(allowsSingle);
        concept.requiresMultiple.addAll(requiresMultiple);
        concept.requiresSingle.addAll(requiresSingle);
        concept.prototypes.addAll(prototypes);
        concept.variables.addAll(variables);
        concept.instances.addAll(instances);
        return concept;
    }

    public static Instance newInstance(String name, List<Facet> facets){
        Instance instance = new Instance();
        instance.name = name;
        instance.facets.addAll(facets);
        return instance;
    }

    public static Prototype newPrototype(String name, List<Facet> facets, String className){
        Prototype prototype = new Prototype();
        prototype.name = name;
        prototype.facets.addAll(facets);
        prototype.className = className;
        return prototype;
    }

    public static Facet newFacet(String name, List<? extends Variable> variables, List<Instance> instances){
        Facet facet = new Facet();
        facet.name = name;
        facet.variables.addAll(variables);
        facet.instances.addAll(instances);
        return facet;
    }

    public static Variable.Integer newIntegerVariable(String name, List<Integer> values){
        return (Variable.Integer) fillVariable(new Variable.Integer(), name, values);
    }

    public static Variable.Double newDoubleVariable(String name, List<Double> values){
        return (Variable.Double) fillVariable(new Variable.Double(), name, values);
    }

    public static Variable.Boolean newBooleanVariable(String name, List<Boolean> values){
        return (Variable.Boolean) fillVariable(new Variable.Boolean(), name, values);
    }

    public static Variable.String newStringVariable(String name, List<String> values){
        return (Variable.String) fillVariable(new Variable.String(), name, values);
    }

    public static Variable.Resource newResourceVariable(String name, List<String> values){
        return (Variable.Resource) fillVariable(new Variable.Resource(), name, values);
    }

    public static Variable.Reference newReferenceVariable(String name, List<String> values){
        return (Variable.Reference) fillVariable(new Variable.Reference(), name, values);
    }

    public static Variable.Word newWordVariable(String name, List<String> values){
        return (Variable.Word) fillVariable(new Variable.Word(), name, values);
    }

    public static Variable.Function newFunctionVariable(String name, List<String> values){
        return (Variable.Function) fillVariable(new Variable.Function(), name, values);
    }

    public static Variable.Date newDateVariable(String name, List<String> values){
        return (Variable.Date) fillVariable(new Variable.Date(), name, values);
    }

    public static Variable.Time newTimeVariable(String name, List<String> values){
        return (Variable.Time) fillVariable(new Variable.Time(), name, values);
    }

    private static Variable fillVariable(Variable variable, String name, List<?> values){
        variable.name = name;
        variable.values = values;
        return variable;
    }

}
