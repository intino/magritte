package monet;

import magritte.Set;

public class Form extends Entity {

    public Set<Field> fieldSet() {
        return _components(Field.class);
    }

    public Field field(int index) {
        return fieldSet().get(index);
    }

}
