package monet.fields;

import siani.tara.magritte.Expression;
import monet.Field;

public class Date extends Field {

	public siani.tara.magritte.primitives.Date value() {
		return _get("value").asDate();
	}

	public void value(siani.tara.magritte.primitives.Date value) {
		_edit().set("value", value);
	}

	public void value(Expression<siani.tara.magritte.primitives.Date> value) {
		_edit().let("value", value);
	}


}
