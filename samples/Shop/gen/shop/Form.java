package shop;


import magritte.Expression;
import magritte.Set;
import magritte.wraps.Morph;
import magritte.wraps.Operation;

public class Form extends Entity {

	public Set<Field> fieldSet() {
		return _components(Field.class);
	}

	public Field field(int index) {
		return fieldSet().get(index);
	}

	public Set<Link> linkSet() {
		return _components(Link.class);
	}

	public Link link(int index) {
		return linkSet().get(index);
	}

	public static class Field extends Morph {

		public String label() {
			return _definition()._get("label").asString();
		}

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

	public static class Link extends Morph {

		public Collection source() {
			return _definition()._get("source").as(Collection.class);
		}

		public Set<Entity> entities() {
			return _getMultiple("entities").as(Entity.class);
		}

		public Entity entities_(int index) {
			return entities().get(index);
		}

		public void entities(Operation operation, Entity... entities) {
			_edit(operation).set("entities", entities);
		}


	}
}
