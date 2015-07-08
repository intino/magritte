package census;

import magritte.Expression;
import magritte.primitives.Date;
import magritte.wraps.Morph;
import monet.Thesaurus;

public class Intervencion extends Morph {

	public Fecha fecha() {
		return _component(Fecha.class);
	}

	public Tipo tipo() {
		return _component(Tipo.class);
	}

	public static class Fecha extends Morph {

		public Date value() {
			return _get("value").asDate();
		}

		public void value(Date value) {
			_edit().set("value", value);
		}

		public void value(Expression<Date> value) {
			_edit().let("value", value);
		}
	}

	public static class Tipo extends Morph {

		public Thesaurus.Term value() {
			return _get("value").as(Thesaurus.Term.class);
		}

		public void value(Thesaurus.Term value) {
			_edit().set("value", value);
		}
	}


}
