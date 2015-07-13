package monet.fields;

import tara.magritte.Expression;
import monet.Field;

public class Date extends Field {

	public tara.magritte.primitives.Date value() {
		return _get("value").asDate();
	}

	public void value(tara.magritte.primitives.Date value) {
		_edit().set("value", value);
	}

	public void value(Expression<tara.magritte.primitives.Date> value) {
		_edit().let("value", value);
	}


}
