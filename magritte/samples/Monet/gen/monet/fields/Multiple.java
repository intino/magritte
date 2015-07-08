package monet.fields;

import magritte.Set;
import magritte.wraps.Type;
import monet.Field;

import static magritte.handlers.Casting.*;

public class Multiple extends Field {

    public Set<Field> fieldSet() {
        return _components(Field.class);
    }

    public Field field(int index) {
        return fieldSet().get(index);
    }

    public Set<Type> fieldTypeSet() {
        return _definition()._aggregables(Field.class);
    }

    public Type fieldType(int index) {
        return fieldTypeSet().get(index);
    }

    public NodeCasting createField(Type type) {
        return _new(type);
    }

    public void deleteField(Field field) {
        super._delete(field);
    }


}
