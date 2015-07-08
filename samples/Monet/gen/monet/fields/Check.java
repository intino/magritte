package monet.fields;

import magritte.Expression;
import monet.Field;

public class Check extends Field {

	public boolean value() {
		return _get("value").asBoolean();
	}

	public void value(boolean value) {
		_edit().set("value", value);
	}

	public void value(Expression<Boolean> value) {
		_edit().let("value", value);
	}

}
