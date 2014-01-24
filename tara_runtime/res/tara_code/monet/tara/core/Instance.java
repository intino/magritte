package tara_code.monet.tara.core;

import java.util.List;

public interface Instance {
    
    public Definition getDefinition();
    
    public Instance getOwner();
    public Instance createChild(Definition definition);
    public List<Instance> getChildren();
    public List<Instance> getChildren(Definition definition);
    
    public InstanceWrapper getWrapper();
    
    public void add(Key key, Object value);
    public Key[] getKeys();
    public Object[] getValues(Key key);
    public Object getSingleValue(Key key);
    
    public interface Key {
        public String name();
    }

}
