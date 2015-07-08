package monet.fields;

import magritte.primitives.Resource;
import monet.Field;

public class Picture extends Field {

	public Resource value() {
		return _get("value").asResource();
	}

	public void value(Resource value) {
		_edit().set("value", value);
	}


}
