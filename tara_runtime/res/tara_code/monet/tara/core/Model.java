package tara_code.monet.tara.core;

import java.util.Iterator;
import monet.tara.toolbox.SmartList;

public class Model implements Iterable<Definition> {
    
    private final Definition scope;
    private final SmartList<Definition> definitionList;

    public Model() {
        this(null);
    }
    
    public Model(Definition scope) {
        this.scope = scope;
        this.definitionList = new SmartList<>();
    }

    public Definition getScope() {
        return scope;
    }
    
    public Definition[] getDefinitions() {
        return definitionList.toArray(new Definition[0]);
    }
    
    public Definition[] getDefinitions(final Definition.Type type) {
        return definitionList.filter(new SmartList.Filter<Definition>() {
            @Override
            public boolean check(Definition definition) {
                return definition.is(type);
            }
        }).toArray(new Definition[0]);
    }
    
    public Definition getDefinition(Definition.Type type) {
        return getDefinitions(type)[0];
    }
    
    public Definition getDefinition(final String id) {
        return definitionList.filter(new SmartList.Filter<Definition>() {
            @Override
            public boolean check(Definition definition) {
                return definition.getId().equals(id);
            }
        }).get(0);
    }
    
    public void add(Definition definition) {
        definition.setScope(scope);
        this.definitionList.add(definition);
    };
    
    public boolean isEmpty() {
        return definitionList.isEmpty();
    }
    
    @Override
    public Iterator<Definition> iterator() {
        return definitionList.iterator();
    }


}
