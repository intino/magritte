package monet;

import magritte.NativeCode;
import magritte.Set;
import magritte.handlers.Casting.NodeCasting;
import magritte.wraps.Morph;
import magritte.wraps.Operation;
import magritte.wraps.Type;

public class Collection extends Entity implements NativeCode {

    public Set<Entry> entrySet() {
        return _components(Entry.class);
    }

    public Entry entry(int index) {
        return entrySet().get(index);
    }

    public Type entryType() {
        return _definition()._aggregables(Entry.class).get(0);
    }

    public NodeCasting newEntry(Type type) {
        return super._new(type);
    }

    public void freeEntry(Entry entry) {
        super._delete(entry);
    }


    public static class Entry extends Morph {

        public Entity entity() {
            return _get("entity").as(Entity.class);
        }

        public void entity(Entity entity) {
            _edit().set("entity", entity);
        }

        public void entity(Operation operation, Entity entity) {
            _edit(operation).set("entity", entity);
        }

        public Set<Type> entityTypes() {
            return _definition()._getAssignable("entity").as(Type.class);
        }

        public Type entityType(int index) {
            return entityTypes().get(index);
        }


    }

}
