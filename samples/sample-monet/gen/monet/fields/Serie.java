package monet.fields;

import siani.tara.magritte.Expression;
import monet.Field;

public class Serie extends Field {

	public String value() {
		return _get("value").asString();
	}

	public void value(String value) {
		_edit().set("value", value);
	}

	public void value(Expression<String> value) {
		_edit().let("value", value);
	}

	public Mode mode() {
		return _definition()._get("mode").asEnumerate(Mode.values());
	}

	public enum Mode {Uppercase, Lowercase}


}
