package census;

import siani.tara.magritte.Expression;
import siani.tara.magritte.Set;
import siani.tara.magritte.primitives.Date;
import siani.tara.magritte.primitives.Resource;
import siani.tara.magritte.wraps.Morph;

public class Animal extends Morph {

	public Codigo codigo() {
		return _component(Codigo.class);
	}

	public Nombre nombre() {
		return _component(Nombre.class);
	}

	public Foto foto() {
		return _component(Foto.class);
	}

	public NumeroChip numeroChip() {
		return _component(NumeroChip.class);
	}

	public FechaNacimiento fechaNacimiento() {
		return _component(FechaNacimiento.class);
	}

	public EsPeligroso esPeligroso() {
		return _component(EsPeligroso.class);
	}

	public Edad edad() {
		return _component(Edad.class);
	}

	public Historial historial() {
		return _component(Historial.class);
	}

	public static class Codigo extends Morph {

		public String value() {
			return _get("value").asString();
		}

		public void value(String value) {
			_edit().set("value", value);
		}

		public void value(Expression<String> value) {
			_edit().let("value", value);
		}
	}

	public static class Nombre extends Morph {

		public String value() {
			return _get("value").asString();
		}

		public void value(String... values) {
			_edit().set("value", values);
		}
	}

	public static class Foto extends Morph {

		public Resource value() {
			return _get("value").asResource();
		}

		public void value(Resource value) {
			_edit().set("value", value);
		}
	}

	public static class NumeroChip extends Morph {

		public String value() {
			return _get("value").asString();
		}

		public void value(String value) {
			_edit().set("value", value);
		}

		public void value(Expression<String> value) {
			_edit().let("value", value);
		}
	}

	public static class FechaNacimiento extends Morph {

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

	public static class EsPeligroso extends Morph {

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

	public static class Edad extends Morph {

		public double value() {
			return _get("value").asDouble();
		}

		public void value(double value) {
			_edit().set("value", value);
		}

		public void value(Expression<Double> value) {
			_edit().let("value", value);
		}
	}

	public static class Historial extends Morph {

		public Set<Intervencion> intervenciones() {
			return _components(Intervencion.class);
		}

		public Intervencion intervencion(int index) {
			return intervenciones().get(index);
		}

		public Intervencion nuevaIntervencion() {
			return super._create(Intervencion.class);
		}

	}

}
