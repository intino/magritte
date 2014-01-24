package tara_code.monet.tara.core;

import monet.tara.toolbox.SmartList;

import java.util.Arrays;
import java.util.List;

public abstract class Definition {

    public static final Type TYPE = new Type();
    private final Id id;
    private final SmartList<Attribute> attributeList;
    private final SmartList<Reference> referenceList;
    private final SmartList<Definition> childrenList;
    private Model innerModel;
    private Definition owner;
    private Definition scope;

    protected Definition() {
        this(null);
    }

    protected Definition(Id id) {
        this.id = id;
        this.attributeList = new SmartList<>();
        this.referenceList = new SmartList<>();
        this.childrenList = new SmartList<>();
        this.innerModel = null;
        this.owner = null;
        this.scope = null;
    }

    public Id getId() {
        return id;
    }

    public String getCodeId() {
        if (id == null)
            return "";
        return id.getCode();
    }

    public String getNameId() {
        if (id == null)
            return "";
        return id.getName();
    }

    public Model getInnerModel() {
        return innerModel;
    }

    public Definition getOwner() {
        return owner;
    }

    private void setOwner(Definition owner) {
        this.owner = owner;
    }

    public Definition getScope() {
        if (owner != null)
            return owner.getScope();
        return scope;
    }

    protected void setScope(Definition scope) {
        this.scope = scope;
    }

    protected void add(Attribute attribute) {
        attributeList.add(attribute);
    }
    
    protected void add(Reference reference) {
        referenceList.add(reference);
    }

    protected void add(Definition definition) {
        definition.setOwner(this);
        childrenList.add(definition);
    }

    protected void add(Model model) {
        checkInnerModelIsCreated();
        for (Definition definition : model)
            innerModel.add(definition);
    }

   protected Attribute getAttribute(final String name) {
        return attributeList.filter(new SmartList.Filter<Attribute>() {
            @Override
            public boolean check(Attribute attribute) {
                return attribute.name.equalsIgnoreCase(name);
            }
        }).get(0);
    }
   
   protected List<Attribute> getAttributes() {
       return (List<Attribute>) attributeList.clone();
   }

    public List<Definition> getChildren() {
        return (List<Definition>) childrenList.clone();
    }

    public List<Definition> getChildren(final Type type) {
        return childrenList.filter(new SmartList.Filter<Definition>() {
            @Override
            public boolean check(Definition definition) {
                return definition.is(type);
            }
        });
    }
    
    public Definition getChild(final Type type) {
        return getChildren(type).get(0);
    }
    
    public Definition getChild(final String id) {
        return childrenList.filter(new SmartList.Filter<Definition>() {
            @Override
            public boolean check(Definition definition) {
                if (definition.id == null) return false;
                return definition.id.equals(id);
            }
        }).get(0);
    }
    
    public Type getType() {
        return TYPE;
    }
    
    public final Type[] getTypes() {
        return getType().getAncestors();
    }

    public final boolean is(Type type) {
        return Arrays.binarySearch(getTypes(), type) != -1;
    }
    

    @Override
    public String toString() {
        if (id == null)
            return "(Anonymous Definition)";
        return "(" + id.name + "," + id.code + ")";
    }

    private void checkInnerModelIsCreated() {
        if (innerModel != null)
            return;
        innerModel = new Model(this);
    }

    public static class Type {
        
        private final Type parent;
        
        private Type() {
            this(null);
        }
        
        public Type(Type parent) {
            this.parent = parent;
        }
        
        private Type[] getAncestors() {
            if (parent == null) return new Type[] { this };
            return add(this, parent.getAncestors());
        }
        
        private Type[] add(Type type, Type[] types) {
            Type[] result = new Type[types.length + 1];
            result[0] = type;
            java.lang.System.arraycopy(types, 0, result, 1, types.length);
            return result;
        }
    }

    public static class Attribute {

        private final String name;
        private final Type type;

        public Attribute(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }
        
        public Type getType() {
            return type;
        }
        
        public interface Type {
            public String name();
        }
        
        public static enum Primitives implements Type {
            BOOLEAN, NATURAL, INTEGER, DOUBLE, DATE, STRING
        }
    }
    
    public static class Reference {
        
        private final String name;
        private final Type definitionType;

        public Reference(String name, Type definitionType) {
            this.name = name;
            this.definitionType = definitionType;
        }

        public String getName() {
            return name;
        }

        public Type getDefinitionType() {
            return definitionType;
        }
        
        
        
    }
    
}