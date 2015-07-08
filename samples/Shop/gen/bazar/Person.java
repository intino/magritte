package bazar;

import magritte.Expression;
import magritte.Set;
import magritte.wraps.Morph;
import magritte.wraps.Operation;

public class Person extends Morph {

	public Set<FullName> fullNameSet() {
		return _components(FullName.class);
	}

	public FullName fullName(int index) {
		return fullNameSet().get(index);
	}

	public Set<Phone> phoneSet() {
		return _components(Phone.class);
	}

	public Phone phone(int index) {
		return phoneSet().get(index);
	}

	public Product product() {
		return _component(Product.class);
	}

	public static class FullName extends Morph {

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

	public static class Phone extends Morph {

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

	public static class Product extends Morph {

		public Set<bazar.Product> entities() {
			return _getMultiple("entities").as(bazar.Product.class);
		}

		public bazar.Product entities_(int index) {
			return entities().get(index);
		}

		public void entities(Operation operation, bazar.Product... entities) {
			_edit(operation).set("entities", entities);
		}

	}
}
