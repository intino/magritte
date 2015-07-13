package monet.fields;

import tara.magritte.Set;
import tara.magritte.handlers.Casting.NodeCasting;
import tara.magritte.wraps.Type;
import monet.Field;

public class Composite extends Field {

	public Set<Field> fields() {
		return _components(Field.class);
	}

	public Field field(int index) {
		return fields().get(index);
	}

	public NodeCasting createField(Type type) {
		return super._new(type);
	}


}
